package com.gustavoms.messagescheduling.message;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduledMessageMapper {

    public static ScheduledMessage createDTOV1ToScheduledMessage(ScheduledMessageCreateDTOV1 dto) {
        return new ScheduledMessage();
    }

    public static ScheduledMessageResponseDTOV1 scheduledMessageToResponseDTOV1(ScheduledMessage entity) {
        return new ScheduledMessageResponseDTOV1();
    }

    public static ScheduledMessageSearchVO searchDTOV1ToVO(ScheduledMessageSearchDTOV1 dto) {
        return new ScheduledMessageSearchVO();
    }

    public static List<ScheduledMessageResponseDTOV1> scheduledMessageListToResponseDTOV1List(
            List<ScheduledMessage> list
    ) {
       return list.stream().map(
               scheduledMessage -> scheduledMessageToResponseDTOV1(scheduledMessage)
       ).collect(Collectors.toList());
    }
}
