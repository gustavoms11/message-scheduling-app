package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.exception.MessageEmptyException;
import com.gustavoms.messagescheduling.exception.ReceiverIdEmptyException;
import com.gustavoms.messagescheduling.exception.ReceiverIdNotAnUUIDException;
import com.gustavoms.messagescheduling.exception.ReceiverPlatformEmptyException;
import com.gustavoms.messagescheduling.exception.ScheduledToDateInThePastException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduledMessageValidator {

    public boolean validateEntityPassedToPersist(ScheduledMessage scheduledMessage)
            throws ReceiverIdEmptyException, ReceiverIdNotAnUUIDException,
            MessageEmptyException, ReceiverPlatformEmptyException,
            ScheduledToDateInThePastException {
        if (scheduledMessage.getReceiverId() == null
                || scheduledMessage.getReceiverId().isEmpty()) {
            throw new ReceiverIdEmptyException();
        }

        if (scheduledMessage.getReceiverId().length() != 36) {
            throw new ReceiverIdNotAnUUIDException(scheduledMessage.getReceiverId());
        }

        if (scheduledMessage.getMessage() == null
                || scheduledMessage.getMessage().isEmpty()) {
            throw new MessageEmptyException();
        }

        if (scheduledMessage.getPlatforms().isEmpty()) {
            throw new ReceiverPlatformEmptyException();
        }

        if (scheduledMessage.getScheduledTo().isBefore(LocalDateTime.now())) {
            throw new ScheduledToDateInThePastException();
        }

        return true;
    }
}
