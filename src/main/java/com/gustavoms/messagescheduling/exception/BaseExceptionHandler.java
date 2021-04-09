package com.gustavoms.messagescheduling.exception;

import com.gustavoms.messagescheduling.converter.DateConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController("BaseExceptionHandler")
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseExceptionResponseDTOV1> handleException(
            Exception exception,
            WebRequest request) {
        var dto = BaseExceptionResponseDTOV1Builder
                .aBaseExceptionResponseDTOV1()
                .code(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .detail(request.getDescription(false))
                .time(DateConverter.fromDate(LocalDateTime.now()))
                .build();

        return new ResponseEntity(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseException.class)
    public final ResponseEntity<BaseExceptionResponseDTOV1> handleBaseException(
            BaseException baseException,
            WebRequest request) {
        var dto = BaseExceptionResponseDTOV1Builder
                .aBaseExceptionResponseDTOV1()
                .code(baseException.getClass().getSimpleName())
                .message(baseException.getMessage())
                .detail(request.getDescription(false))
                .time(DateConverter.fromDate(LocalDateTime.now()))
                .build();

        return new ResponseEntity(dto, HttpStatus.valueOf(baseException.getStatusCode()));
    }
}
