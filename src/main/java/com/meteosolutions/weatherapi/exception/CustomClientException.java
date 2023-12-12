package com.meteosolutions.weatherapi.exception;

public class CustomClientException extends RuntimeException{
    public CustomClientException(String message){
        super(message);
    }
}
