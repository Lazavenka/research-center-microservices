package com.roger.orderservice.exception;


public class IncorrectRequestException extends RuntimeException{

    public IncorrectRequestException(String message) {
        super(message);
    }

}
