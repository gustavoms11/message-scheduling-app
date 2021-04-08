package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.exception.ScheduleStatusNotFoundException;

public enum ScheduleStatus {

    SCHEDULED,
    SENT;

    static ScheduleStatus fromString(String status) throws ScheduleStatusNotFoundException {
        try {
            return ScheduleStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new ScheduleStatusNotFoundException(status);
        }
    }
}
