package com.gustavoms.messagescheduling.exception;

public class MessageEmptyException extends BaseException {

    public MessageEmptyException() {
        super("Message must be filled to be scheduled");
    }
}
