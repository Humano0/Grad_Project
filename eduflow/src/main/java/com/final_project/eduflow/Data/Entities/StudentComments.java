package com.final_project.eduflow.Data.Entities;

import com.final_project.eduflow.Data.Entities.IdClasses.StudentCommentsId;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "student_comments")
@IdClass(StudentCommentsId.class)
public class StudentComments {

    @Id
    @Column(name = "request_when_created")
    private OffsetDateTime requestWhenCreated;

    @Id
    @Column(name = "request_student_id")
    private long requestStudentId;

    @Id
    @Column(name = "request_type_id")
    private int requestTypeId;

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_comment")
    private String userComment;

    @Id
    @Column(name = "time_posted")
    private OffsetDateTime timePosted;

    public StudentComments(OffsetDateTime requestWhenCreated, int requestStudentId, int requestTypeId, int userId, String userComment) {
        this.requestWhenCreated = requestWhenCreated;
        this.requestStudentId = requestStudentId;
        this.requestTypeId = requestTypeId;
        this.userId = userId;
        this.userComment = userComment;
        this.timePosted = OffsetDateTime.now();
    }

    public StudentComments() {
    }

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

    public int getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(int requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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