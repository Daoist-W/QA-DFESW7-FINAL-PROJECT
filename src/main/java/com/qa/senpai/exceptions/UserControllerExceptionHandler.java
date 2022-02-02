package com.qa.senpai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler {


    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<String> userNotFoundExceptions(UserNotFoundException unfe) {
        return new ResponseEntity<>(unfe.getMessage(), HttpStatus.NOT_FOUND);
    }

}
