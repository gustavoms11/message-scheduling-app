package com.gustavoms.messagescheduling.exception;

public class ReceiverPlatformNotFoundException extends BaseException {

    public ReceiverPlatformNotFoundException(String platform) {
        super(String.format("Receiver platform %s not found", platform));
    }
}
