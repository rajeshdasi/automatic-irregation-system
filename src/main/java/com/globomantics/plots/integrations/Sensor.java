package com.globomantics.plots.integrations;

import java.time.ZonedDateTime;

public interface Sensor {

    void registerPlotForWatering(Integer plotNumber, ZonedDateTime timeOfWatering, Integer waterRequired);

}
