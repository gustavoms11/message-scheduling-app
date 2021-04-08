package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.converter.DateConverter;
import com.gustavoms.messagescheduling.exception.BadFormattedDateException;
import com.gustavoms.messagescheduling.exception.BaseException;
import com.gustavoms.messagescheduling.exception.MessageEmptyException;
import com.gustavoms.messagescheduling.exception.ReceiverIdEmptyException;
import com.gustavoms.messagescheduling.exception.ReceiverIdNotAnUUIDException;
import com.gustavoms.messagescheduling.exception.ReceiverPlatformEmptyException;
import com.gustavoms.messagescheduling.exception.ScheduledToDateInThePastException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class ScheduledMessageValidatorTest {

    @InjectMocks
    private ScheduledMessageValidator scheduledMessageValidator;

    @Test
    public void whenValidateEntityPassedToPersistWithoutReceiverId() {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .build();

        assertThatThrownBy(() -> scheduledMessageValidator.validateEntityPassedToPersist(entity))
                .isInstanceOf(ReceiverIdEmptyException.class);
    }

    @Test
    public void whenValidateEntityPassedToPersistWithReceiverIdEmpty() {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .build();

        assertThatThrownBy(() -> scheduledMessageValidator.validateEntityPassedToPersist(entity))
                .isInstanceOf(ReceiverIdEmptyException.class);
    }

    @Test
    public void whenValidateEntityPassedToPersistWithReceiverIdInvalid() {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .receiverId("TEST")
                .build();

        assertThatThrownBy(() -> scheduledMessageValidator.validateEntityPassedToPersist(entity))
                .isInstanceOf(ReceiverIdNotAnUUIDException.class);
    }

    @Test
    public void whenValidateEntityPassedToPersistWithoutMessage() {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .receiverId(UUID.randomUUID().toString())
                .build();

        assertThatThrownBy(() -> scheduledMessageValidator.validateEntityPassedToPersist(entity))
                .isInstanceOf(MessageEmptyException.class);
    }

    @Test
    public void whenValidateEntityPassedToPersistWithMessageEmpty() {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .receiverId(UUID.randomUUID().toString())
                .message("")
                .build();

        assertThatThrownBy(() -> scheduledMessageValidator.validateEntityPassedToPersist(entity))
                .isInstanceOf(MessageEmptyException.class);
    }

    @Test
    public void whenValidateEntityPassedToPersistWithoutPlatform() {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .receiverId(UUID.randomUUID().toString())
                .message("TESTE")
                .build();

        assertThatThrownBy(() -> scheduledMessageValidator.validateEntityPassedToPersist(entity))
                .isInstanceOf(ReceiverPlatformEmptyException.class);
    }

    @Test
    public void whenValidateEntityPassedToPersistWithPastScheduledToDate() throws BadFormattedDateException {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .receiverId(UUID.randomUUID().toString())
                .message("TESTE")
                .platforms(Set.of(ReceiverPlatform.SMS))
                .scheduledTo(DateConverter.toDate("2000-01-01T10:00:00Z"))
                .build();

        assertThatThrownBy(() -> scheduledMessageValidator.validateEntityPassedToPersist(entity))
                .isInstanceOf(ScheduledToDateInThePastException.class);
    }

    @Test
    public void whenValidateEntityPassedToPersistValid() throws BaseException {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .receiverId(UUID.randomUUID().toString())
                .message("TESTE")
                .platforms(Set.of(ReceiverPlatform.SMS))
                .scheduledTo(LocalDateTime.now().plusDays(1))
                .build();

        var result = scheduledMessageValidator.validateEntityPassedToPersist(entity);

        assertThat(result).isTrue();
    }
}
