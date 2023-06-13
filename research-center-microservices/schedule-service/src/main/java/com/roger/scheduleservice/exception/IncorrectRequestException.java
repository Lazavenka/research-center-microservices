package com.roger.scheduleservice.exception;


public class IncorrectRequestException extends RuntimeException{

    public IncorrectRequestException(String message) {
        super(message);
    }

}
