package com.final_project.eduflow.Data.Entities;

import com.final_project.eduflow.Data.Entities.IdClasses.StudentNotiType;
import com.final_project.eduflow.Data.Entities.IdClasses.StudentNotificationId;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "student_notification")
@IdClass(StudentNotificationId.class)
public class StudentNotification {

    @Id
    @Column(name = "request_when_created")
    private OffsetDateTime requestWhenCreated;

    @Id
    @Column(name = "request_student_id")
    private long requestStudentId;

    @Id
    @Column(name = "request_type_id")
    private long requestTypeId;

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "haveseen")
    private boolean haveSeen;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private StudentNotiType notiType;

    @Id
    @Column(name = "time_posted")
    private OffsetDateTime timePosted;

    public StudentNotification() {
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

    public boolean isHaveSeen() {
        return haveSeen;
    }

    public void setHaveSeen(boolean haveSeen) {
        this.haveSeen = haveSeen;
    }

    public StudentNotiType getNotiType() {
        return notiType;
    }

    public void setNotiType(StudentNotiType notiType) {
        this.notiType = notiType;
    }

    public OffsetDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(OffsetDateTime timePosted) {
        this.timePosted = timePosted;
    }
}