package com.roger.microservices.exception;


public class IncorrectRequestException extends RuntimeException{

    private String originalThrowable;
    public IncorrectRequestException(String message) {
        super(message);
    }

}
