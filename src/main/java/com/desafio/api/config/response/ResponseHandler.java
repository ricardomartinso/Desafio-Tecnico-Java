package com.desafio.api.config.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new HashMap<>();

        response.put("data", responseObject);
        response.put("message", message);
        response.put("httpStatus", httpStatus);

        return new ResponseEntity<>(response, httpStatus);
    }
}
