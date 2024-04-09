package com.final_project.eduflow.Presentation.ResponseClasses;
import java.time.OffsetDateTime;

public class CommentRequest {
    private String commentMessage;
    private Long requestStudentId;
    private OffsetDateTime requestWhenCreated;
    private Long requestTypeId;

    public CommentRequest(String commentMessage, Long requestStudentId, OffsetDateTime requestWhenCreated, Long requestTypeId) {
        this.commentMessage = commentMessage;
        this.requestStudentId = requestStudentId;
        this.requestWhenCreated = requestWhenCreated;
        this.requestTypeId = requestTypeId;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Long getRequestStudentId() {
        return requestStudentId;
    }

    public void setRequestStudentId(Long requestStudentId) {
        this.requestStudentId = requestStudentId;
    }

    public OffsetDateTime getRequestWhenCreated() {
        return requestWhenCreated;
    }

    public void setRequestWhenCreated(OffsetDateTime requestWhenCreated) {
        this.requestWhenCreated = requestWhenCreated;
    }

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }
}
