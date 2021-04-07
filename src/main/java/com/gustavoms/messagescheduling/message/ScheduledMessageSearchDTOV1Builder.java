package com.gustavoms.messagescheduling.message;

public final class ScheduledMessageSearchDTOV1Builder {
    private ScheduledMessageSearchDTOV1 scheduledMessageSearchDTOV1;

    private ScheduledMessageSearchDTOV1Builder() {
        scheduledMessageSearchDTOV1 = new ScheduledMessageSearchDTOV1();
    }

    public static ScheduledMessageSearchDTOV1Builder aScheduledMessageSearchDTOV1() {
        return new ScheduledMessageSearchDTOV1Builder();
    }

    public ScheduledMessageSearchDTOV1Builder page(int page) {
        scheduledMessageSearchDTOV1.setPage(page);
        return this;
    }

    public ScheduledMessageSearchDTOV1Builder pageSize(int pageSize) {
        scheduledMessageSearchDTOV1.setPageSize(pageSize);
        return this;
    }

    public ScheduledMessageSearchDTOV1Builder order(String order) {
        scheduledMessageSearchDTOV1.setOrder(order);
        return this;
    }

    public ScheduledMessageSearchDTOV1Builder receiverId(String receiverId) {
        scheduledMessageSearchDTOV1.setReceiverId(receiverId);
        return this;
    }

    public ScheduledMessageSearchDTOV1Builder createdAtStartDate(String createdAtStartDate) {
        scheduledMessageSearchDTOV1.setCreatedAtStartDate(createdAtStartDate);
        return this;
    }

    public ScheduledMessageSearchDTOV1Builder createdAtEndDate(String createdAtEndDate) {
        scheduledMessageSearchDTOV1.setCreatedAtEndDate(createdAtEndDate);
        return this;
    }

    public ScheduledMessageSearchDTOV1Builder scheduledToStartDate(String scheduledToStartDate) {
        scheduledMessageSearchDTOV1.setScheduledToStartDate(scheduledToStartDate);
        return this;
    }

    public ScheduledMessageSearchDTOV1Builder scheduledToEndDate(String scheduledToEndDate) {
        scheduledMessageSearchDTOV1.setScheduledToEndDate(scheduledToEndDate);
        return this;
    }

    public ScheduledMessageSearchDTOV1 build() {
        return scheduledMessageSearchDTOV1;
    }
}
