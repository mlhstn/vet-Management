package com.vetManagement.spring.core.config.exception;

public class recordAlreadyExistException extends RuntimeException {

    private final Long id;

    public recordAlreadyExistException(Long id) {
        super(id + " is exist");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
