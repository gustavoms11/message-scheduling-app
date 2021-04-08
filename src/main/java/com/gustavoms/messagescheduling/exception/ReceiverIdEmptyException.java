package com.gustavoms.messagescheduling.exception;

public class ReceiverIdEmptyException extends BaseException {

    public ReceiverIdEmptyException() {
        super("Receiver id must be filled to create a new scheduled message");
    }
}
