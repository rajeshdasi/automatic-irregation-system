package com.globomantics.plots.models.exceptions;

public class PlotRegistrationException extends Exception {
    private Long plotNumber;
    private String registrationFailureException;

    public PlotRegistrationException(String message, Long plotNumber, String registrationFailureException) {
        super(message);
        this.plotNumber = plotNumber;
        this.registrationFailureException = registrationFailureException;
    }
}
