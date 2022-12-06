package com.globomantics.plots.integrations;

import org.springframework.stereotype.Component;

@Component
public class PagerDuty implements Alert {
    @Override
    public void sendWateringStatus(boolean status, String message) {

    }
}
