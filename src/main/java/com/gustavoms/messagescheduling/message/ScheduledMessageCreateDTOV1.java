package com.gustavoms.messagescheduling.message;

import java.util.Set;

public class ScheduledMessageCreateDTOV1 {

    private String receiverId;
    private String scheduledTo;
    private Set<String> platforms;
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
