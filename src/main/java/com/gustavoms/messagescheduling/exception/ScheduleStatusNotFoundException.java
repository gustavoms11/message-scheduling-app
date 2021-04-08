package com.gustavoms.messagescheduling.exception;

public class ScheduleStatusNotFoundException extends BaseException {

    public ScheduleStatusNotFoundException(String status) {
        super(String.format("Scheduled status %s not found", status));
    }
}
