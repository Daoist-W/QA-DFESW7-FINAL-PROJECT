package com.qa.senpai.exceptions;

import javax.persistence.EntityNotFoundException;

public class AvailabilityNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 3L;

    public AvailabilityNotFoundException() {
    }

    public AvailabilityNotFoundException(String message) {
        super(message);
    }
}
