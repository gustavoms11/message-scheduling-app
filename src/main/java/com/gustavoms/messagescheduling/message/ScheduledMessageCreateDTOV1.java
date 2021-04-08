package com.gustavoms.messagescheduling.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedHashSet;
import java.util.Set;

@ApiModel(value = "ScheduledMessageCreateDTOV1")
public class ScheduledMessageCreateDTOV1 {

    @ApiModelProperty(value = "Receiver object ID", required = true)
    private String receiverId;

    @ApiModelProperty(value = "Message scheduled date, must follow ISO-8601 pattern",
            example = "2020-01-01T10:00:00",
            required = true)
    private String scheduledTo;

    @ApiModelProperty(value = "Receiver platforms receiving the message",
            required = true,
            allowableValues = "MAIL,SMS,WHATSAPP,PUSH")
    private Set<String> platforms = new LinkedHashSet<>();

    @ApiModelProperty(value = "Message to be sent", required = true)
    private String message;

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

}
