package com.final_project.eduflow.Presentation.ResponseClasses;

import java.time.OffsetDateTime;

public class GetCommentId {
    private OffsetDateTime requestWhenCreated;
    private Long requestStudentId;
    private Long requestTypeId;

    public GetCommentId(OffsetDateTime requestWhenCreated, Long requestStudentId, Long requestTypeId) {
        this.requestWhenCreated = requestWhenCreated;
        this.requestStudentId = requestStudentId;
        this.requestTypeId = requestTypeId;
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
