package com.roger.microservices.exception;

public class CustomWebServiceException extends RuntimeException{

    public CustomWebServiceException(String message) {
        super(message);
    }

}
