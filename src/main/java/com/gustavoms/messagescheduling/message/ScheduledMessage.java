package com.gustavoms.messagescheduling.message;


import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "SCHEDULED_MESSAGE")
public class ScheduledMessage {

    @Id
    @Column(name = "ID", length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "SCHEDULED_TO")
    private LocalDateTime scheduledTo;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "RECEIVER_ID", length = 36)
    private String receiverId;

    @Lob
    @Column(name = "MESSAGE")
    private String message;

    @ElementCollection(targetClass = ReceiverPlatform.class)
    @CollectionTable(name = "SCHEDULED_MESSAGE_RECEIVER_PLATFORM", joinColumns = @JoinColumn(name = "ID"))
    @Column(name = "RECEIVER_PLATFORM_ID", length = 36)
    @Enumerated(EnumType.STRING)
    private Set<ReceiverPlatform> platforms;

    @Column(name = "RECEIVER_TYPE", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private ReceiverType receiverType;

    @Column(name = "SCHEDULE_STATUS", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getScheduledTo() {
        return scheduledTo;
    }

    public void setScheduledTo(LocalDateTime scheduledTo) {
        this.scheduledTo = scheduledTo;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<ReceiverPlatform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<ReceiverPlatform> platforms) {
        this.platforms = platforms;
    }

    public ReceiverType getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(ReceiverType receiverType) {
        this.receiverType = receiverType;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledMessage that = (ScheduledMessage) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ScheduledMessage{" +
                "id='" + id + '\'' +
                ", scheduledTo=" + scheduledTo +
                ", receiverId='" + receiverId + '\'' +
                ", message='" + message + '\'' +
                ", platforms=" + platforms +
                ", receiverType='" + receiverType + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
