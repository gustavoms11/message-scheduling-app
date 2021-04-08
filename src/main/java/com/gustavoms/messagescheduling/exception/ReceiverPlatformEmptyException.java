package com.gustavoms.messagescheduling.exception;

public class ReceiverPlatformEmptyException extends BaseException {

    public ReceiverPlatformEmptyException() {
        super("A scheduled message must be sending to at least one platform");
    }
}
