package com.desafio.api.config.exception;

import org.springframework.http.HttpStatus;

public class ApiExceptionMessage extends Exception {
    private static final long serialVersionUID = 1L;
    protected final HttpStatus status;

    public ApiExceptionMessage(HttpStatus status, String error) {
        super(error);
        this.status = status;

    }
    // Getters and Setters
}