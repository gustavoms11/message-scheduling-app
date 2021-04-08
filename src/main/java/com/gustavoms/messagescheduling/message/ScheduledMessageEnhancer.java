package com.gustavoms.messagescheduling.message;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ScheduledMessageEnhancer {

    public ScheduledMessage enhanceToCreate(ScheduledMessage scheduledMessage) {
        scheduledMessage.setId(UUID.randomUUID().toString());
        scheduledMessage.setCreatedAt(LocalDateTime.now());
        scheduledMessage.setReceiverType(ReceiverType.USER);
        scheduledMessage.setStatus(ScheduleStatus.SCHEDULED);

        return scheduledMessage;
    }
}
