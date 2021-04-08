package com.gustavoms.messagescheduling.exception;

public class ScheduledToDateInThePastException extends BaseException {

    public ScheduledToDateInThePastException() {
        super("Date to schedule a message must be in the future");
    }
}
