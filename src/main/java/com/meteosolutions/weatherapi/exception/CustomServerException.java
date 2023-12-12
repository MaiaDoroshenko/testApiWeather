package com.meteosolutions.weatherapi.exception;

public class CustomServerException extends RuntimeException{
    public CustomServerException(String message) {
        super(message);
    }
}
