package com.gustavoms.messagescheduling.message;

import java.util.Set;

public final class ScheduledMessageCreateDTOV1Builder {
    private ScheduledMessageCreateDTOV1 scheduledMessageCreateDTOV1;

    private ScheduledMessageCreateDTOV1Builder() {
        scheduledMessageCreateDTOV1 = new ScheduledMessageCreateDTOV1();
    }

    public static ScheduledMessageCreateDTOV1Builder aScheduledMessageCreateDTOV1() {
        return new ScheduledMessageCreateDTOV1Builder();
    }

    public ScheduledMessageCreateDTOV1Builder receiverId(String receiverId) {
        scheduledMessageCreateDTOV1.setReceiverId(receiverId);
        return this;
    }

    public ScheduledMessageCreateDTOV1Builder scheduledTo(String scheduledTo) {
        scheduledMessageCreateDTOV1.setScheduledTo(scheduledTo);
        return this;
    }

    public ScheduledMessageCreateDTOV1Builder platforms(Set<String> platforms) {
        scheduledMessageCreateDTOV1.setPlatforms(platforms);
        return this;
    }

    public ScheduledMessageCreateDTOV1Builder message(String message) {
        scheduledMessageCreateDTOV1.setMessage(message);
        return this;
    }

    public ScheduledMessageCreateDTOV1 build() {
        return scheduledMessageCreateDTOV1;
    }
}
