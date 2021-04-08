package com.gustavoms.messagescheduling.exception;

public class BaseException extends Exception {

    private int statusCode = 400;

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
