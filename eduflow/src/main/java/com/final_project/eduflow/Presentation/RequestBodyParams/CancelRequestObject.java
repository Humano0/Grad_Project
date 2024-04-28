package com.final_project.eduflow.Presentation.RequestBodyParams;

import java.time.OffsetDateTime;

public class CancelRequestObject {
    private OffsetDateTime requestWhenCreated;
    private Long requestStudentId;
    private Long requestTypeId;
    private String cancellationReason;

    public CancelRequestObject(OffsetDateTime requestWhenCreated, Long requestStudentId, Long requestTypeId, String cancellationReason) {
        this.requestWhenCreated = requestWhenCreated;
        this.requestStudentId = requestStudentId;
        this.requestTypeId = requestTypeId;
        this.cancellationReason = cancellationReason;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public OffsetDateTime getRequestWhenCreated() {
        return requestWhenCreated;
    }

    public void setRequestWhenCreated(OffsetDateTime requestWhenCreated) {
        this.requestWhenCreated = requestWhenCreated;
    }

    public Long getRequestStudentId() {
        return requestStudentId;
    }

    public void setRequestStudentId(Long requestStudentId) {
        this.requestStudentId = requestStudentId;
    }

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }
}
