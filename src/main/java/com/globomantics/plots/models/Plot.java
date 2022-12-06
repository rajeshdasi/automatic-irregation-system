package com.globomantics.plots.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "plot")
public class Plot {

    @Id
    @Column(name = "plotNumber", nullable = false)
    private Integer plotNumber;

    @Column(name = "plotOwner", nullable = false)
    private String plotOwner;

    @Column(name = "plantedCrop")
    private String plantedCrop;

    @Column(name = "waterRequired")
    private Integer waterRequired;

    @Column(name = "timeOfWatering")
    private ZonedDateTime timeOfWatering;

    public Plot(Integer plotNumber, String plotOwner, String plantedCrop, Integer waterRequired, ZonedDateTime timeOfWatering) {
        this.plotNumber = plotNumber;
        this.plotOwner = plotOwner;
        this.plantedCrop = plantedCrop;
        this.waterRequired = waterRequired;
        this.timeOfWatering = timeOfWatering;
    }

    public Plot(Plot oldPlot) {
        this.plotNumber = oldPlot.getPlotNumber();
        this.plotOwner = oldPlot.getPlotOwner();
        this.plantedCrop = oldPlot.getPlantedCrop();
        this.waterRequired = oldPlot.getWaterRequired();
        this.timeOfWatering = oldPlot.getTimeOfWatering();
    }

    public Integer getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(Integer plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getPlotOwner() {
        return plotOwner;
    }

    public void setPlotOwner(String plotOwner) {
        this.plotOwner = plotOwner;
    }

    public String getPlantedCrop() {
        return plantedCrop;
    }

    public void setPlantedCrop(String plantedCrop) {
        this.plantedCrop = plantedCrop;
    }

    public Integer getWaterRequired() {
        return waterRequired;
    }

    public void setWaterRequired(Integer waterRequired) {
        this.waterRequired = waterRequired;
    }

    public ZonedDateTime getTimeOfWatering() {
        return timeOfWatering;
    }

    public void setTimeOfWatering(ZonedDateTime timeOfWatering) {
        this.timeOfWatering = timeOfWatering;
    }

    @Override
    public String toString() {
        return String.format("Plot: plotNumber: %d, plantedCrop: %s, waterRequired: %d, timeOfWatering: %s",
                this.getPlotNumber(),
                this.getPlantedCrop(),
                this.getWaterRequired(),
                this.getTimeOfWatering().toString());
    }

    public Plot() {
    }
}
