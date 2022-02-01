package com.qa.senpai.exceptions;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    // https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {

    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
