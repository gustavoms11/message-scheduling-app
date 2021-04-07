package com.gustavoms.messagescheduling.message;

import java.time.LocalDateTime;
import java.util.Set;

public final class ScheduledMessageBuilder {
    private ScheduledMessage scheduledMessage;

    private ScheduledMessageBuilder() {
        scheduledMessage = new ScheduledMessage();
    }

    public static ScheduledMessageBuilder aScheduledMessage() {
        return new ScheduledMessageBuilder();
    }

    public ScheduledMessageBuilder id(String id) {
        scheduledMessage.setId(id);
        return this;
    }

    public ScheduledMessageBuilder scheduledTo(LocalDateTime scheduledTo) {
        scheduledMessage.setScheduledTo(scheduledTo);
        return this;
    }

    public ScheduledMessageBuilder receiverId(String receiverId) {
        scheduledMessage.setReceiverId(receiverId);
        return this;
    }

    public ScheduledMessageBuilder message(String message) {
        scheduledMessage.setMessage(message);
        return this;
    }

    public ScheduledMessageBuilder platforms(Set<ReceiverPlatform> platforms) {
        scheduledMessage.setPlatforms(platforms);
        return this;
    }

    public ScheduledMessageBuilder receiverType(ReceiverType receiverType) {
        scheduledMessage.setReceiverType(receiverType);
        return this;
    }

    public ScheduledMessageBuilder status(ScheduleStatus status) {
        scheduledMessage.setStatus(status);
        return this;
    }

    public ScheduledMessageBuilder createdAt(LocalDateTime createdAt) {
        scheduledMessage.setCreatedAt(createdAt);
        return this;
    }

    public ScheduledMessage build() {
        return scheduledMessage;
    }
}
