package com.final_project.eduflow.Data.Entities;

import com.final_project.eduflow.Data.Entities.IdClasses.StaffCommentsId;
import com.final_project.eduflow.Presentation.ResponseClasses.CommentRequest;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "staff_comments")
@IdClass(StaffCommentsId.class)
public class StaffComments {

    @Id
    @Column(name = "request_when_created")
    private OffsetDateTime requestWhenCreated;

    @Id
    @Column(name = "request_student_id")
    private Long requestStudentId;

    @Id
    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_comment")
    private String userComment;

    @Id
    @Column(name = "time_posted")
    private OffsetDateTime timePosted;

    public StaffComments(OffsetDateTime requestWhenCreated, Long requestStudentId, Long requestTypeId, Long userId, String userComment) {
        this.requestWhenCreated = requestWhenCreated;
        this.requestStudentId = requestStudentId;
        this.requestTypeId = requestTypeId;
        this.userId = userId;
        this.userComment = userComment;
        this.timePosted = OffsetDateTime.now();
    }

    public StaffComments() {
    }

    public StaffComments(CommentRequest commentRequest, Long userId) {
        this.requestWhenCreated = commentRequest.getRequestWhenCreated();
        this.requestStudentId = commentRequest.getRequestStudentId();
        this.requestTypeId = commentRequest.getRequestTypeId();
        this.userId = userId;
        this.userComment = commentRequest.getCommentMessage();
        this.timePosted = OffsetDateTime.now();
    }

    // getters and setters

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public OffsetDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(OffsetDateTime timePosted) {
        this.timePosted = timePosted;
    }
}