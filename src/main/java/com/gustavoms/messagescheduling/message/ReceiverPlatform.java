package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.exception.ReceiverPlatformNotFoundException;

import java.util.LinkedHashSet;
import java.util.Set;

public enum ReceiverPlatform {

    MAIL,
    SMS,
    PUSH,
    WHATSAPP;

    static ReceiverPlatform fromString(String platform) throws ReceiverPlatformNotFoundException {
        try {
            return ReceiverPlatform.valueOf(platform);
        } catch (IllegalArgumentException e) {
            throw new ReceiverPlatformNotFoundException(platform);
        }
    }

    static Set<ReceiverPlatform> fromStringSet(Set<String> platforms) throws ReceiverPlatformNotFoundException {
        var result = new LinkedHashSet<ReceiverPlatform>();

        for (var platform : platforms) {
            result.add(fromString(platform));
        }

        return result;
    }


}
