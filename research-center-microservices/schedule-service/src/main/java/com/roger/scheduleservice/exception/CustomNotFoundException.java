package com.roger.scheduleservice.exception;


public class CustomNotFoundException extends RuntimeException {

    private Long id;

    public CustomNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
