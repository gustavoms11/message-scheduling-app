package com.gustavoms.messagescheduling.message;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("ScheduledMessageControllerV1")
@RequestMapping(value = "/api/v1/scheduled-messages")
public class ScheduledMessageControllerV1 {

    private ScheduledMessageService scheduledMessageService;

    public ScheduledMessageControllerV1(
            ScheduledMessageService scheduledMessageService) {
        this.scheduledMessageService = scheduledMessageService;
    }

    @PostMapping
    public ResponseEntity<ScheduledMessageResponseDTOV1> post(
            @RequestBody ScheduledMessageCreateDTOV1 scheduledMessage
    ) {
        var entity = ScheduledMessageMapper
                .createDTOV1ToScheduledMessage(scheduledMessage);
        var persistedEntity = scheduledMessageService.create(entity);
        return ResponseEntity.ok(ScheduledMessageMapper.scheduledMessageToResponseDTOV1(persistedEntity));
    }

    @GetMapping
    public ResponseEntity<List<ScheduledMessageResponseDTOV1>> get(
            ScheduledMessageSearchDTOV1 search
    ) {
        var vo = ScheduledMessageMapper.searchDTOV1ToVO(search);
        var list = scheduledMessageService.search(vo);
        return ResponseEntity.ok(ScheduledMessageMapper.scheduledMessageListToResponseDTOV1List(list));
    }

    @GetMapping(value = "/{scheduled-message-id}")
    public ResponseEntity<ScheduledMessageResponseDTOV1> singleGet(
        @PathVariable("scheduled-message-id") String scheduledMessageId
    ) {
        var entity = scheduledMessageService.find(scheduledMessageId);
        return ResponseEntity.ok(ScheduledMessageMapper.scheduledMessageToResponseDTOV1(entity));
    }

    @DeleteMapping(value = "/{scheduled-mesage-id}")
    public ResponseEntity delete(
            @PathVariable("scheduled-message-id") String scheduledMessageId
    ) {
        scheduledMessageService.delete(scheduledMessageId);
        return ResponseEntity.noContent().build();
    }
}
