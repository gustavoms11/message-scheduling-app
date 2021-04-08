package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.converter.DateConverter;
import com.gustavoms.messagescheduling.exception.BadFormattedDateException;
import com.gustavoms.messagescheduling.exception.ReceiverPlatformNotFoundException;
import com.gustavoms.messagescheduling.exception.ScheduleStatusNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduledMessageMapperTest {

    @Test
    public void whenScheduledMessageToResponseDTOV1ThenReturnDTO() throws BadFormattedDateException {
        var localDate1 = DateConverter.toDate("2021-01-02T11:11:22Z");
        var localDate2 = DateConverter.toDate("2021-01-02T11:11:23Z");

        var entity = ScheduledMessageBuilder.aScheduledMessage()
                .createdAt(localDate1)
                .id("SCHEDULEDID")
                .message("MESSAGE")
                .platforms(Set.of(ReceiverPlatform.MAIL, ReceiverPlatform.PUSH))
                .receiverId("RECEIVERID")
                .receiverType(ReceiverType.USER)
                .scheduledTo(localDate2)
                .status(ScheduleStatus.SCHEDULED)
                .build();

        var result = ScheduledMessageMapper.scheduledMessageToResponseDTOV1(entity);

        assertThat(result.getCreatedAt()).isEqualTo("2021-01-02T11:11:22Z");
        assertThat(result.getId()).isEqualTo("SCHEDULEDID");
        assertThat(result.getMessage()).isEqualTo("MESSAGE");
        assertThat(result.getPlatforms()).hasSize(2);
        assertThat(result.getPlatforms()).containsExactlyInAnyOrder("MAIL","PUSH");
        assertThat(result.getReceiverId()).isEqualTo("RECEIVERID");
        assertThat(result.getStatus()).isEqualTo("SCHEDULED");
        assertThat(result.getScheduledTo()).isEqualTo("2021-01-02T11:11:23Z");
    }

    @Test
    public void whenCreateDTOV1ToScheduledMessageThenReturnEntity()
            throws BadFormattedDateException, ReceiverPlatformNotFoundException {
        var createDTO = ScheduledMessageCreateDTOV1Builder
                .aScheduledMessageCreateDTOV1()
                .message("MESSAGE")
                .platforms(Set.of("MAIL","WHATSAPP","SMS"))
                .receiverId("RECEIVERID")
                .scheduledTo("2021-01-01T14:30:22Z")
                .build();

        var result = ScheduledMessageMapper.createDTOV1ToScheduledMessage(createDTO);

        assertThat(result.getMessage()).isEqualTo("MESSAGE");
        assertThat(result.getPlatforms()).hasSize(3);
        assertThat(result.getPlatforms()).containsExactlyInAnyOrder(
                ReceiverPlatform.MAIL, ReceiverPlatform.WHATSAPP, ReceiverPlatform.SMS);
        assertThat(result.getReceiverId()).isEqualTo("RECEIVERID");
        assertThat(result.getScheduledTo().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511422000L);
    }

    @Test
    public void whenSearchDTOV1ToVOThenReturnSearchVO() throws BadFormattedDateException, ScheduleStatusNotFoundException {
        var searchDTO = ScheduledMessageSearchDTOV1Builder
                .aScheduledMessageSearchDTOV1()
                .page(3)
                .pageSize(117)
                .order("ORDER")
                .status("SENT")
                .receiverId("RECEIVERID")
                .createdAtStartDate("2021-01-01T14:30:22Z")
                .createdAtEndDate("2021-01-01T14:30:23Z")
                .scheduledToStartDate("2021-01-01T14:30:24Z")
                .scheduledToEndDate("2021-01-01T14:30:25Z")
                .build();

        var result = ScheduledMessageMapper.searchDTOV1ToVO(searchDTO);

        assertThat(result.getPage()).isEqualTo(3);
        assertThat(result.getPageSize()).isEqualTo(117);
        assertThat(result.getOrder()).isEqualTo("ORDER");
        assertThat(result.getStatus()).isEqualTo(ScheduleStatus.SENT);
        assertThat(result.getReceiverId()).isEqualTo("RECEIVERID");
        assertThat(result.getCreatedAtStartDate().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511422000L);
        assertThat(result.getCreatedAtEndDate().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511423000L);
        assertThat(result.getScheduledToStartDate().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511424000L);
        assertThat(result.getScheduledToEndDate().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511425000L);
    }

    @Test
    public void whenSearchDTOV1WithStatusNullToVOThenReturnSearchVOWithoutStatusSetted()
            throws BadFormattedDateException, ScheduleStatusNotFoundException {
        var searchDTO = ScheduledMessageSearchDTOV1Builder
                .aScheduledMessageSearchDTOV1()
                .page(3)
                .pageSize(117)
                .order("ORDER")
                .receiverId("RECEIVERID")
                .createdAtStartDate("2021-01-01T14:30:22Z")
                .createdAtEndDate("2021-01-01T14:30:23Z")
                .scheduledToStartDate("2021-01-01T14:30:24Z")
                .scheduledToEndDate("2021-01-01T14:30:25Z")
                .build();

        var result = ScheduledMessageMapper.searchDTOV1ToVO(searchDTO);

        assertThat(result.getPage()).isEqualTo(3);
        assertThat(result.getPageSize()).isEqualTo(117);
        assertThat(result.getOrder()).isEqualTo("ORDER");
        assertThat(result.getStatus()).isNull();
        assertThat(result.getReceiverId()).isEqualTo("RECEIVERID");
        assertThat(result.getCreatedAtStartDate().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511422000L);
        assertThat(result.getCreatedAtEndDate().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511423000L);
        assertThat(result.getScheduledToStartDate().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511424000L);
        assertThat(result.getScheduledToEndDate().toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511425000L);
    }

    @Test
    public void whenSearchDTOV1WithDatesNullToVOThenReturnSearchVOWithoutDatesSetted()
            throws BadFormattedDateException, ScheduleStatusNotFoundException {
        var searchDTO = ScheduledMessageSearchDTOV1Builder
                .aScheduledMessageSearchDTOV1()
                .page(3)
                .pageSize(117)
                .order("ORDER")
                .receiverId("RECEIVERID")
                .build();

        var result = ScheduledMessageMapper.searchDTOV1ToVO(searchDTO);

        assertThat(result.getPage()).isEqualTo(3);
        assertThat(result.getPageSize()).isEqualTo(117);
        assertThat(result.getOrder()).isEqualTo("ORDER");
        assertThat(result.getStatus()).isNull();
        assertThat(result.getReceiverId()).isEqualTo("RECEIVERID");
        assertThat(result.getCreatedAtStartDate()).isNull();
        assertThat(result.getCreatedAtEndDate()).isNull();
        assertThat(result.getScheduledToStartDate()).isNull();
        assertThat(result.getScheduledToEndDate()).isNull();
    }

    @Test
    public void whenScheduleMessageListToResponseDTOV1ListThenReturnList() {
        var entity = ScheduledMessageBuilder
                .aScheduledMessage()
                .id("ID")
                .createdAt(LocalDateTime.now())
                .scheduledTo(LocalDateTime.now())
                .status(ScheduleStatus.SCHEDULED)
                .platforms(new LinkedHashSet<>())
                .build();
        var list = List.of(entity);

        var result = ScheduledMessageMapper.scheduledMessageListToResponseDTOV1List(list);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("ID");
    }
}
