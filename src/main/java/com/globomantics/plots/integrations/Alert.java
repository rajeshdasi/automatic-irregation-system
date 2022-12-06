package com.globomantics.plots.integrations;

public interface Alert {

    void sendWateringStatus(boolean status, String message);
}
