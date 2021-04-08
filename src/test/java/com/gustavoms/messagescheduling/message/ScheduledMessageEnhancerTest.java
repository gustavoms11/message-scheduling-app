package com.gustavoms.messagescheduling.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ScheduledMessageEnhancerTest {

    @InjectMocks
    private ScheduledMessageEnhancer scheduledMessageEnhancer;

    @Test
    public void whenEnhanceThenReturnFilledEntity() {
        var scheduledMessage = ScheduledMessageBuilder.aScheduledMessage().build();

        var result = scheduledMessageEnhancer.enhanceToCreate(scheduledMessage);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getReceiverType()).isEqualTo(ReceiverType.USER);
        assertThat(result.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(result.getStatus()).isEqualTo(ScheduleStatus.SCHEDULED);
    }
}
