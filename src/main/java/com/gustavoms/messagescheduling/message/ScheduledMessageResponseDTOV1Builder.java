package com.gustavoms.messagescheduling.message;

import java.util.Set;

public final class ScheduledMessageResponseDTOV1Builder {
    private ScheduledMessageResponseDTOV1 scheduledMessageResponseDTOV1;

    private ScheduledMessageResponseDTOV1Builder() {
        scheduledMessageResponseDTOV1 = new ScheduledMessageResponseDTOV1();
    }

    public static ScheduledMessageResponseDTOV1Builder aScheduledMessageResponseDTOV1() {
        return new ScheduledMessageResponseDTOV1Builder();
    }

    public ScheduledMessageResponseDTOV1Builder id(String id) {
        scheduledMessageResponseDTOV1.setId(id);
        return this;
    }

    public ScheduledMessageResponseDTOV1Builder receiverId(String receiverId) {
        scheduledMessageResponseDTOV1.setReceiverId(receiverId);
        return this;
    }

    public ScheduledMessageResponseDTOV1Builder scheduledTo(String scheduledTo) {
        scheduledMessageResponseDTOV1.setScheduledTo(scheduledTo);
        return this;
    }

    public ScheduledMessageResponseDTOV1Builder platforms(Set<String> platforms) {
        scheduledMessageResponseDTOV1.setPlatforms(platforms);
        return this;
    }

    public ScheduledMessageResponseDTOV1Builder message(String message) {
        scheduledMessageResponseDTOV1.setMessage(message);
        return this;
    }

    public ScheduledMessageResponseDTOV1Builder status(String status) {
        scheduledMessageResponseDTOV1.setStatus(status);
        return this;
    }

    public ScheduledMessageResponseDTOV1Builder createdAt(String createdAt) {
        scheduledMessageResponseDTOV1.setCreatedAt(createdAt);
        return this;
    }

    public ScheduledMessageResponseDTOV1 build() {
        return scheduledMessageResponseDTOV1;
    }
}
