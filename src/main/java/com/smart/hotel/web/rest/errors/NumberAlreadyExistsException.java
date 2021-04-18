package com.smart.hotel.web.rest.errors;

public class NumberAlreadyExistsException extends BadRequestAlertException {

    public NumberAlreadyExistsException() {
        super("Number already exists", "number", "numberesists");
    }
}