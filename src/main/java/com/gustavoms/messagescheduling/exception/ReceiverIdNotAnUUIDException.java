package com.gustavoms.messagescheduling.exception;

public class ReceiverIdNotAnUUIDException extends BaseException {

    public ReceiverIdNotAnUUIDException(String receiverId) {
        super(String.format("Receiver id %s is not a valid UUID", receiverId));
    }
}
