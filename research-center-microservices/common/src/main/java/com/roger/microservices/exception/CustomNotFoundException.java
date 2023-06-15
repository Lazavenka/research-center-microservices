package com.roger.microservices.exception;


public class CustomNotFoundException extends RuntimeException {

    private Long id;

    public CustomNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
