package com.roger.orderservice.exception;

public class CustomWebServiceException extends RuntimeException{

    public CustomWebServiceException(String message) {
        super(message);
    }

}
