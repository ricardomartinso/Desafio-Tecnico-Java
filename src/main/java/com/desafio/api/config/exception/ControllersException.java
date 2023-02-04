package com.desafio.api.config.exception;

import org.springframework.http.HttpStatus;

public class ControllersException extends ApiExceptionMessage {
    private static final long serialVersionUID = 1L;

    public ControllersException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
