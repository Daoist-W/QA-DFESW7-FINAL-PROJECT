package com.qa.senpai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JobControllerExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<String> jobNotFoundException(JobNotFoundException jnfe) {
        return new ResponseEntity<>(jnfe.getMessage(), HttpStatus.NOT_FOUND);
    }
}
