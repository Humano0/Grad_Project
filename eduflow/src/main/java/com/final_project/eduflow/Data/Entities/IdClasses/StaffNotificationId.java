package com.final_project.eduflow.Data.Entities.IdClasses;

import java.time.OffsetDateTime;

public class StaffNotificationId {

    private OffsetDateTime requestWhenCreated;

    private long requestStudentId;

    private long requestTypeId;

    private long userId;

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

    public long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public OffsetDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(OffsetDateTime timePosted) {
        this.timePosted = timePosted;
    }
}