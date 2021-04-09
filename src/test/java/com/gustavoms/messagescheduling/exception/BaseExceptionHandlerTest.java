package com.gustavoms.messagescheduling.exception;

import com.gustavoms.messagescheduling.converter.DateConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BaseExceptionHandlerTest {

    @InjectMocks
    private BaseExceptionHandler baseExceptionHandler;

    @Test
    public void whenHandleNeutralExceptionThenReturnDTO() throws BadFormattedDateException {
        var exception = new Exception("Neutral exception");
        var request = Mockito.mock(WebRequest.class);

        when(request.getDescription(false)).thenReturn("DESCRIPTION");

        var result = baseExceptionHandler.handleException(
                exception, request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getBody().getCode()).isEqualTo("Exception");
        assertThat(result.getBody().getDetail()).isEqualTo("DESCRIPTION");
        assertThat(result.getBody().getMessage()).isEqualTo("Neutral exception");
        assertThat(DateConverter.toDate(result.getBody().getTime())).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    public void whenHandleBaseExceptionThenReturnDTO() throws BadFormattedDateException {
        var exception = new BaseException("Base exception");
        var request = Mockito.mock(WebRequest.class);

        when(request.getDescription(false)).thenReturn("DESCRIPTION");

        var result = baseExceptionHandler.handleBaseException(
                exception, request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().getCode()).isEqualTo("BaseException");
        assertThat(result.getBody().getDetail()).isEqualTo("DESCRIPTION");
        assertThat(result.getBody().getMessage()).isEqualTo("Base exception");
        assertThat(DateConverter.toDate(result.getBody().getTime())).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    public void whenHandleExtendedBaseExceptionThenReturnDTO() throws BadFormattedDateException {
        var exception = new ScheduledMessageNotFoundException("ID");
        var request = Mockito.mock(WebRequest.class);

        when(request.getDescription(false)).thenReturn("DESCRIPTION");

        var result = baseExceptionHandler.handleBaseException(
                exception, request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getCode()).isEqualTo("ScheduledMessageNotFoundException");
        assertThat(result.getBody().getDetail()).isEqualTo("DESCRIPTION");
        assertThat(result.getBody().getMessage()).isEqualTo("Scheduled message with id ID not found");
        assertThat(DateConverter.toDate(result.getBody().getTime())).isBeforeOrEqualTo(LocalDateTime.now());
    }
}
