package com.gustavoms.messagescheduling.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends Exception {

    private int statusCode = HttpStatus.BAD_REQUEST.value();

    public BaseException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
