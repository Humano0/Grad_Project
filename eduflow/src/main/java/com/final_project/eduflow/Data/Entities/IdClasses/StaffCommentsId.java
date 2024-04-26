package com.final_project.eduflow.Data.Entities.IdClasses;

import java.time.OffsetDateTime;

public class StaffCommentsId {

    private OffsetDateTime requestWhenCreated;

    private Long requestStudentId;

    private Long requestTypeId;

    private Long userId;

    private OffsetDateTime timePosted;

    // getters and setters

    public OffsetDateTime getRequestWhenCreated() {
        return requestWhenCreated;
    }

    public void setRequestWhenCreated(OffsetDateTime requestWhenCreated) {
        this.requestWhenCreated = requestWhenCreated;
    }

    public long getRequestStudentId() {
        return requestStudentId;
    }

    public void setRequestStudentId(long requestStudentId) {
        this.requestStudentId = requestStudentId;
    }

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OffsetDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(OffsetDateTime timePosted) {
        this.timePosted = timePosted;
    }
}