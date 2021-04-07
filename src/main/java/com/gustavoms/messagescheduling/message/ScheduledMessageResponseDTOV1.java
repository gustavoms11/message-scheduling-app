package com.gustavoms.messagescheduling.message;

import java.util.Set;

public class ScheduledMessageResponseDTOV1 {

    private String id;
    private String receiverId;
    private String scheduledTo;
    private String createdAt;
    private Set<String> platforms;
    private String message;
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
