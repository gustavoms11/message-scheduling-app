package com.gustavoms.messagescheduling.exception;

public final class BaseExceptionResponseDTOV1Builder {
    private BaseExceptionResponseDTOV1 baseExceptionResponseDTOV1;

    private BaseExceptionResponseDTOV1Builder() {
        baseExceptionResponseDTOV1 = new BaseExceptionResponseDTOV1();
    }

    public static BaseExceptionResponseDTOV1Builder aBaseExceptionResponseDTOV1() {
        return new BaseExceptionResponseDTOV1Builder();
    }

    public BaseExceptionResponseDTOV1Builder code(String code) {
        baseExceptionResponseDTOV1.setCode(code);
        return this;
    }

    public BaseExceptionResponseDTOV1Builder message(String message) {
        baseExceptionResponseDTOV1.setMessage(message);
        return this;
    }

    public BaseExceptionResponseDTOV1Builder detail(String detail) {
        baseExceptionResponseDTOV1.setDetail(detail);
        return this;
    }

    public BaseExceptionResponseDTOV1Builder time(String time) {
        baseExceptionResponseDTOV1.setTime(time);
        return this;
    }

    public BaseExceptionResponseDTOV1 build() {
        return baseExceptionResponseDTOV1;
    }
}
