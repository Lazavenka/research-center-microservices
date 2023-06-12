package com.roger.orderservice.exception;

public class CustomWebServiceException extends RuntimeException{
    public CustomWebServiceException() {
    }

    public CustomWebServiceException(String message) {
        super(message);
    }

    public CustomWebServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomWebServiceException(Throwable cause) {
        super(cause);
    }
}
