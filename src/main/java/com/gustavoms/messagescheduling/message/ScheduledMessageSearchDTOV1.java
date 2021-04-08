package com.gustavoms.messagescheduling.message;

import io.swagger.annotations.ApiParam;

public class ScheduledMessageSearchDTOV1 {

    @ApiParam(value = "Page param", example = "1", defaultValue = "1")
    private int page = 1;

    @ApiParam(value = "Page size param", example = "20", defaultValue = "20")
    private int pageSize = 20;

    @ApiParam(value = "Order by param", allowableValues = "createdAt,scheduledTo")
    private String order;

    @ApiParam(value = "Filter by receiver object id")
    private String receiverId;

    @ApiParam(value = "Start filter range createdAt date")
    private String createdAtStartDate;

    @ApiParam(value = "End filter range createdAt date")
    private String createdAtEndDate;

    @ApiParam(value = "Start filter range scheduledTo date")
    private String scheduledToStartDate;

    @ApiParam(value = "End filter range scheduledTo date")
    private String scheduledToEndDate;

    @ApiParam(value = "Actual schedule status")
    private String status;

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

    public String getCreatedAtStartDate() {
        return createdAtStartDate;
    }

    public void setCreatedAtStartDate(String createdAtStartDate) {
        this.createdAtStartDate = createdAtStartDate;
    }

    public String getCreatedAtEndDate() {
        return createdAtEndDate;
    }

    public void setCreatedAtEndDate(String createdAtEndDate) {
        this.createdAtEndDate = createdAtEndDate;
    }

    public String getScheduledToStartDate() {
        return scheduledToStartDate;
    }

    public void setScheduledToStartDate(String scheduledToStartDate) {
        this.scheduledToStartDate = scheduledToStartDate;
    }

    public String getScheduledToEndDate() {
        return scheduledToEndDate;
    }

    public void setScheduledToEndDate(String scheduledToEndDate) {
        this.scheduledToEndDate = scheduledToEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
