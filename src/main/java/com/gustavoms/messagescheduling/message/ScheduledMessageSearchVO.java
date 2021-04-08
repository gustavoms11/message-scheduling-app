package com.gustavoms.messagescheduling.message;

import java.time.LocalDateTime;

public class ScheduledMessageSearchVO {

    private int page = 1;
    private int pageSize = 20;
    private String order;
    private String receiverId;
    private LocalDateTime createdAtStartDate;
    private LocalDateTime createdAtEndDate;
    private LocalDateTime scheduledToStartDate;
    private LocalDateTime scheduledToEndDate;
    private ScheduleStatus status;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public LocalDateTime getCreatedAtStartDate() {
        return createdAtStartDate;
    }

    public void setCreatedAtStartDate(LocalDateTime createdAtStartDate) {
        this.createdAtStartDate = createdAtStartDate;
    }

    public LocalDateTime getCreatedAtEndDate() {
        return createdAtEndDate;
    }

    public void setCreatedAtEndDate(LocalDateTime createdAtEndDate) {
        this.createdAtEndDate = createdAtEndDate;
    }

    public LocalDateTime getScheduledToStartDate() {
        return scheduledToStartDate;
    }

    public void setScheduledToStartDate(LocalDateTime scheduledToStartDate) {
        this.scheduledToStartDate = scheduledToStartDate;
    }

    public LocalDateTime getScheduledToEndDate() {
        return scheduledToEndDate;
    }

    public void setScheduledToEndDate(LocalDateTime scheduledToEndDate) {
        this.scheduledToEndDate = scheduledToEndDate;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }
}
