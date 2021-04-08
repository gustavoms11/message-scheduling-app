package com.gustavoms.messagescheduling.message;

import java.time.LocalDateTime;

public final class ScheduledMessageSearchVOBuilder {
    private ScheduledMessageSearchVO scheduledMessageSearchVO;

    private ScheduledMessageSearchVOBuilder() {
        scheduledMessageSearchVO = new ScheduledMessageSearchVO();
    }

    public static ScheduledMessageSearchVOBuilder aScheduledMessageSearchVO() {
        return new ScheduledMessageSearchVOBuilder();
    }

    public ScheduledMessageSearchVOBuilder page(int page) {
        scheduledMessageSearchVO.setPage(page);
        return this;
    }

    public ScheduledMessageSearchVOBuilder pageSize(int pageSize) {
        scheduledMessageSearchVO.setPageSize(pageSize);
        return this;
    }

    public ScheduledMessageSearchVOBuilder order(String order) {
        scheduledMessageSearchVO.setOrder(order);
        return this;
    }

    public ScheduledMessageSearchVOBuilder receiverId(String receiverId) {
        scheduledMessageSearchVO.setReceiverId(receiverId);
        return this;
    }

    public ScheduledMessageSearchVOBuilder createdAtStartDate(LocalDateTime createdAtStartDate) {
        scheduledMessageSearchVO.setCreatedAtStartDate(createdAtStartDate);
        return this;
    }

    public ScheduledMessageSearchVOBuilder createdAtEndDate(LocalDateTime createdAtEndDate) {
        scheduledMessageSearchVO.setCreatedAtEndDate(createdAtEndDate);
        return this;
    }

    public ScheduledMessageSearchVOBuilder scheduledToStartDate(LocalDateTime scheduledToStartDate) {
        scheduledMessageSearchVO.setScheduledToStartDate(scheduledToStartDate);
        return this;
    }

    public ScheduledMessageSearchVOBuilder scheduledToEndDate(LocalDateTime scheduledToEndDate) {
        scheduledMessageSearchVO.setScheduledToEndDate(scheduledToEndDate);
        return this;
    }

    public ScheduledMessageSearchVOBuilder status(ScheduleStatus status) {
        scheduledMessageSearchVO.setStatus(status);
        return this;
    }

    public ScheduledMessageSearchVO build() {
        return scheduledMessageSearchVO;
    }
}
