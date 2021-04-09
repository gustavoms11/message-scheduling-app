package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.exception.BadFormattedDateException;
import com.gustavoms.messagescheduling.exception.BaseException;
import com.gustavoms.messagescheduling.exception.ReceiverPlatformNotFoundException;
import com.gustavoms.messagescheduling.exception.ScheduleStatusNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Api(value = "Message scheduling API", tags = "Scheduling Message API")
public class ScheduledMessageControllerV1 {

    private ScheduledMessageService scheduledMessageService;

    public ScheduledMessageControllerV1(
            ScheduledMessageService scheduledMessageService) {
        this.scheduledMessageService = scheduledMessageService;
    }

    @ApiOperation(value = "Schedule a new message")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Scheduled successfully"),
            @ApiResponse(code = 400, message = "Not able to schedule due to bad request")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduledMessageResponseDTOV1> post(
            @RequestBody ScheduledMessageCreateDTOV1 scheduledMessage
    ) throws BaseException {
        var entity = ScheduledMessageMapper
                .createDTOV1ToScheduledMessage(scheduledMessage);
        var persistedEntity = scheduledMessageService.create(entity);
        return new ResponseEntity<>(
                ScheduledMessageMapper.scheduledMessageToResponseDTOV1(persistedEntity),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Search for scheduled messages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Search successful")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScheduledMessageResponseDTOV1>> get(
            ScheduledMessageSearchDTOV1 search
    ) throws BadFormattedDateException, ScheduleStatusNotFoundException {
        var vo = ScheduledMessageMapper.searchDTOV1ToVO(search);
        var list = scheduledMessageService.search(vo);
        return ResponseEntity.ok(ScheduledMessageMapper.scheduledMessageListToResponseDTOV1List(list));
    }

    @ApiOperation(value = "Find a single scheduled message based on ID")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Schedule found successfully"),
            @ApiResponse(code = 404, message = "Schedule not found")
    })
    @GetMapping(value = "/{scheduled-message-id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduledMessageResponseDTOV1> singleGet(
        @ApiParam(value = "Scheduled Message ID") @PathVariable("scheduled-message-id") String scheduledMessageId
    ) throws BaseException {
        var entity = scheduledMessageService.find(scheduledMessageId);
        return ResponseEntity.ok(ScheduledMessageMapper.scheduledMessageToResponseDTOV1(entity));
    }

    @ApiOperation("Delete a single scheduled message")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Schedule deleted successfully"),
            @ApiResponse(code = 404, message = "Schedule not found")
    })
    @DeleteMapping(value = "/{scheduled-message-id}")
    public ResponseEntity delete(
            @ApiParam(value = "Scheduled Message ID") @PathVariable("scheduled-message-id") String scheduledMessageId
    ) throws BaseException {
        scheduledMessageService.delete(scheduledMessageId);
        return ResponseEntity.noContent().build();
    }
}
