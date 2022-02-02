package com.qa.senpai.exceptions;

import javax.persistence.EntityNotFoundException;

public class JobNotFoundException extends EntityNotFoundException {

    // https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
    private static final long serialVersionUID = 2L;

    public JobNotFoundException() {

    }

    public JobNotFoundException(String message) {
        super(message);
    }

}
