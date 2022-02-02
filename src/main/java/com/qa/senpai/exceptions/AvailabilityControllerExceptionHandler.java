package com.qa.senpai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AvailabilityControllerExceptionHandler {

    @ExceptionHandler(value = {AvailabilityNotFoundException.class})
    public ResponseEntity<String> availabilityNotFoundException(AvailabilityNotFoundException anfe) {
        return new ResponseEntity<>(anfe.getMessage(), HttpStatus.NOT_FOUND);
    }
}
