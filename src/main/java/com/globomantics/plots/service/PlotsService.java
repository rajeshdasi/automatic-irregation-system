package com.globomantics.plots.service;

import com.globomantics.plots.integrations.Alert;
import com.globomantics.plots.models.Plot;
import com.globomantics.plots.repository.PlotRepository;
import com.globomantics.plots.integrations.Sensor;
import com.globomantics.plots.models.exceptions.BadRequest;
import com.globomantics.plots.models.exceptions.PlotRegistrationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PlotsService {

    private final PlotRepository plotRepository;
    private final Sensor sensor;
    private final Alert alert;

    public PlotsService(PlotRepository plotRepository, Sensor sensor, Alert alert) {
        this.plotRepository = plotRepository;
        this.sensor = sensor;
        this.alert = alert;
    }

    public Plot getPlot(Integer plotNumber) throws BadRequest {
        Optional<Plot> plot = plotRepository.findByPlotNumber(plotNumber);
        if (plot.isPresent()) {
            return plot.get();
        } else {
            throw new BadRequest(String.format("Plot with number %d is not present", plotNumber));
        }
    }

    public List<Plot> getAllPlots() {
        return plotRepository.findAll();
    }

    public Plot createPlot(Plot plot, Integer plotNumber) {
        Plot newPlot = new Plot(plot);
        newPlot.setPlotNumber(plotNumber);

        registerPlotConfigurationsWithSensor(plotNumber, newPlot.getTimeOfWatering(), newPlot.getWaterRequired());
        return plotRepository.save(newPlot);
    }


    @Retryable(include = PlotRegistrationException.class, backoff = @Backoff(delay = 300))
    void registerPlotConfigurationsWithSensor(Integer plotNumber, ZonedDateTime timeOfWatering, Integer waterRequired) {
        sensor.registerPlotForWatering(plotNumber, timeOfWatering, waterRequired);
    }

    @Recover
    void recover(PlotRegistrationException ex, Integer plotNumber, ZonedDateTime timeOfWatering, Integer waterRequired) {
        alert.sendWateringStatus(false,
                String.format("Watering of %d litres failed for plot %d at time %s",
                        waterRequired, plotNumber, timeOfWatering.toString()));
    }

    public Plot updatePlot(Plot plot, Integer plotNumber) throws BadRequest {
        this.getPlot(plotNumber);
        return this.createPlot(plot, plotNumber);
    }

    public void deletePlot(Integer plotNumber) {
        plotRepository.deleteById(plotNumber);
    }
}
