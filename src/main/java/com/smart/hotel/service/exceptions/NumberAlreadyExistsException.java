package com.smart.hotel.service.exceptions;


public class NumberAlreadyExistsException extends RuntimeException {

    public NumberAlreadyExistsException() {
        super("Number already exists");
    }
}
