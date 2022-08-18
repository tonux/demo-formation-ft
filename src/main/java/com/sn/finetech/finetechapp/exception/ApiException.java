package com.sn.finetech.finetechapp.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private HttpStatus status;
    private String message;
    private Throwable throwable;

    public ApiException(HttpStatus httpStatus, String message, Throwable throwable) {
        super(message);
        this.status = httpStatus;
        this.throwable = throwable;
    }
}
