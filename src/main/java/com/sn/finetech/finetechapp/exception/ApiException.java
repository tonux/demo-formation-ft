package com.sn.finetech.finetechapp.exception;

import org.springframework.http.HttpStatus;


public class ApiException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
    private final String message;

    public ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ApiException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

}
