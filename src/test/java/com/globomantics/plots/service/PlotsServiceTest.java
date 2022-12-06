package com.globomantics.plots.service;

import com.globomantics.plots.integrations.AgriculturalSensor;
import com.globomantics.plots.integrations.PagerDuty;
import com.globomantics.plots.models.Plot;
import com.globomantics.plots.models.exceptions.BadRequest;
import com.globomantics.plots.repository.PlotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlotsServiceTest {

    @Mock
    private PlotRepository plotRepository;

    @Mock
    private PagerDuty pagerDuty;

    @Mock
    private AgriculturalSensor sensor;

    @InjectMocks
    private PlotsService plotsService;

    @Test
    void shouldCallSensorOnPlotRegistration(){
        doNothing().when(sensor).registerPlotForWatering(any(), any(), any());
        when(plotRepository.save(any())).thenReturn(new Plot());

        plotsService.createPlot(new Plot(), 1);

        verify(sensor, times(1)).registerPlotForWatering(any(), any(), any());
    }

    @Test
    void shouldCallSensorOnUpdatePlot() throws BadRequest {
        when(plotRepository.findByPlotNumber(any())).thenReturn(Optional.of(new Plot()));
        doNothing().when(sensor).registerPlotForWatering(any(), any(), any());
        when(plotRepository.save(any())).thenReturn(new Plot());

        plotsService.updatePlot(new Plot(), 1);

        verify(sensor, times(1)).registerPlotForWatering(any(), any(), any());
    }
}