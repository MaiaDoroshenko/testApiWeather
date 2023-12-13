package com.meteosolutions.weatherapi.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
        super("City not found");
    }

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
