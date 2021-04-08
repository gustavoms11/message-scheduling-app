package com.gustavoms.messagescheduling.exception;

public class BadFormattedDateException extends BaseException {

    public BadFormattedDateException(String date) {
        super(String.format("Date passed by request %s could not be parsed", date));
    }
}
