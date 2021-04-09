package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.exception.BaseException;
import com.gustavoms.messagescheduling.exception.ScheduledMessageNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduledMessageServiceTest {

    @InjectMocks
    private ScheduledMessageService scheduledMessageService;

    @Mock
    private ScheduledMessageRepository scheduledMessageRepository;

    @Mock
    private ScheduledMessageValidator scheduledMessageValidator;

    @Mock
    private ScheduledMessageEnhancer scheduledMessageEnhancer;

    @Test
    public void whenCreateScheduledMessageThenReturnNewEntity() throws BaseException {
        var scheduledMessageToCreate = ScheduledMessageBuilder
                .aScheduledMessage()
                .id("ID")
                .build();

        when(scheduledMessageEnhancer.enhanceToCreate(scheduledMessageToCreate))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        when(scheduledMessageRepository.save(scheduledMessageToCreate))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        var result = scheduledMessageService.create(scheduledMessageToCreate);

        assertThat(result).isEqualTo(scheduledMessageToCreate);

        verify(scheduledMessageValidator).validateEntityPassedToPersist(scheduledMessageToCreate);
        verify(scheduledMessageEnhancer).enhanceToCreate(scheduledMessageToCreate);
        verify(scheduledMessageRepository).save(scheduledMessageToCreate);
    }

    @Test
    public void whenFindScheduledEntityByIdThenReturnEntity() throws ScheduledMessageNotFoundException {
        var scheduledMessageEntity = ScheduledMessageBuilder
                .aScheduledMessage()
                .id("ID")
                .build();

        when(scheduledMessageRepository.findById("ID")).thenReturn(Optional.of(scheduledMessageEntity));

        var result = scheduledMessageService.find("ID");

        assertThat(result).isEqualTo(scheduledMessageEntity);

        verify(scheduledMessageRepository).findById("ID");
    }

    @Test
    public void whenFindScheduledMessageNotFoundThenThrowException() {
        assertThatThrownBy(() -> scheduledMessageService.find("ID"))
                .isInstanceOf(ScheduledMessageNotFoundException.class);

        verify(scheduledMessageRepository).findById("ID");
    }

    @Test
    public void whenDeleteScheduledEntityByIdThenVoid() throws ScheduledMessageNotFoundException {
        var scheduledMessageEntity = ScheduledMessageBuilder
                .aScheduledMessage()
                .id("ID")
                .build();

        when(scheduledMessageRepository.findById("ID")).thenReturn(Optional.of(scheduledMessageEntity));

        scheduledMessageService.delete("ID");

        verify(scheduledMessageRepository).findById("ID");
        verify(scheduledMessageRepository).delete(scheduledMessageEntity);
    }

    @Test
    public void whenDeleteScheduledEntityNotFoundThenThrowException() {
        assertThatThrownBy(() -> scheduledMessageService.delete("ID"))
                .isInstanceOf(ScheduledMessageNotFoundException.class);

        verify(scheduledMessageRepository).findById("ID");
        verify(scheduledMessageRepository, times(0)).delete(any(ScheduledMessage.class));
    }
}
