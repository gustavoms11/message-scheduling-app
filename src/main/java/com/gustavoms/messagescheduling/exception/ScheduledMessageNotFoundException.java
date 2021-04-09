package com.gustavoms.messagescheduling.exception;

import org.springframework.http.HttpStatus;

public class ScheduledMessageNotFoundException extends BaseException {

    public ScheduledMessageNotFoundException(String scheduledMessageId) {
        super(String.format("Scheduled message with id %s not found", scheduledMessageId));
        setStatusCode(HttpStatus.NOT_FOUND.value());
    }
}
