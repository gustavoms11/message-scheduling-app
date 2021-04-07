package com.gustavoms.messagescheduling.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

@ApiModel("ScheduledMessageResponseDTOV1")
public class ScheduledMessageResponseDTOV1 {

    @ApiModelProperty(value = "Scheduled Message ID")
    private String id;

    @ApiModelProperty(value = "Receiver object ID")
    private String receiverId;

    @ApiModelProperty(value = "Message scheduled date")
    private String scheduledTo;

    @ApiModelProperty(value = "Create at this date")
    private String createdAt;

    @ApiModelProperty(value = "Set of platforms to send this message",
            allowableValues = "MAIL,SMS,WHATSAPP,PUSH")
    private Set<String> platforms;

    @ApiModelProperty(value = "Message to be sent")
    private String message;

    @ApiModelProperty(value = "Scheduling status", allowableValues = "SCHEDULED,SENT")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getScheduledTo() {
        return scheduledTo;
    }

    public void setScheduledTo(String scheduledTo) {
        this.scheduledTo = scheduledTo;
    }

    public Set<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<String> platforms) {
        this.platforms = platforms;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
