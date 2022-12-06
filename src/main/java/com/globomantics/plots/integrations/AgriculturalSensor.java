package com.globomantics.plots.integrations;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class AgriculturalSensor implements Sensor {
    @Override
    public void registerPlotForWatering(Integer plotNumber, ZonedDateTime timeOfWatering, Integer waterRequired) {

    }
}
