package com.globomantics.plots.interceptors;

import com.globomantics.plots.controller.PlotsController;
import com.globomantics.plots.models.exceptions.BadRequest;
import com.globomantics.plots.models.exceptions.PlotsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {PlotsController.class})
public class ControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Object> handleBadRequest(BadRequest badRequest) {
        return new ResponseEntity<>(new PlotsException(badRequest.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleInternalServerError(Throwable e) {
        LOGGER.error("Internal Server Error", e);
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
