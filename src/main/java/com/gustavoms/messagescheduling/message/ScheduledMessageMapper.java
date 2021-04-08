package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.converter.DateConverter;
import com.gustavoms.messagescheduling.exception.BadFormattedDateException;
import com.gustavoms.messagescheduling.exception.ReceiverPlatformNotFoundException;
import com.gustavoms.messagescheduling.exception.ScheduleStatusNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduledMessageMapper {

    public static ScheduledMessage createDTOV1ToScheduledMessage(ScheduledMessageCreateDTOV1 dto)
    throws BadFormattedDateException, ReceiverPlatformNotFoundException {
        var result = new ScheduledMessage();
        result.setMessage(dto.getMessage());
        result.setPlatforms(ReceiverPlatform.fromStringSet(dto.getPlatforms()));
        result.setReceiverId(dto.getReceiverId());
        result.setScheduledTo(dto.getScheduledTo() != null ? DateConverter.toDate(dto.getScheduledTo()) : null);

        return result;
    }

    public static ScheduledMessageResponseDTOV1 scheduledMessageToResponseDTOV1(ScheduledMessage entity) {
        var result = new ScheduledMessageResponseDTOV1();
        result.setCreatedAt(DateConverter.fromDate(entity.getCreatedAt()));
        result.setStatus(entity.getStatus().name());
        result.setId(entity.getId());
        result.setMessage(entity.getMessage());
        result.setPlatforms(entity.getPlatforms().stream().map(ReceiverPlatform::name)
                .collect(Collectors.toSet()));
        result.setReceiverId(entity.getReceiverId());
        result.setScheduledTo(DateConverter.fromDate(entity.getScheduledTo()));

        return result;
    }

    public static ScheduledMessageSearchVO searchDTOV1ToVO(ScheduledMessageSearchDTOV1 dto)
    throws BadFormattedDateException, ScheduleStatusNotFoundException {
        var result = new ScheduledMessageSearchVO();
        result.setPage(dto.getPage());
        result.setPageSize(dto.getPageSize());
        result.setOrder(dto.getOrder());
        result.setReceiverId(dto.getReceiverId());
        result.setCreatedAtStartDate(dto.getCreatedAtStartDate() != null ? DateConverter.toDate(dto.getCreatedAtStartDate()) : null);
        result.setCreatedAtEndDate(dto.getCreatedAtEndDate() != null ? DateConverter.toDate(dto.getCreatedAtEndDate()) : null);
        result.setScheduledToStartDate(dto.getScheduledToStartDate() != null ? DateConverter.toDate(dto.getScheduledToStartDate()) : null);
        result.setScheduledToEndDate(dto.getScheduledToEndDate() != null ? DateConverter.toDate(dto.getScheduledToEndDate()) : null);
        result.setStatus(dto.getStatus() != null ? ScheduleStatus.fromString(dto.getStatus()) : null);

        return result;
    }

    public static List<ScheduledMessageResponseDTOV1> scheduledMessageListToResponseDTOV1List(
            List<ScheduledMessage> list
    ) {
       return list.stream().map(
               scheduledMessage -> scheduledMessageToResponseDTOV1(scheduledMessage)
       ).collect(Collectors.toList());
    }
}
