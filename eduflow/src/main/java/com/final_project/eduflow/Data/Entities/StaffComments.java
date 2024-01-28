package com.final_project.eduflow.Data.Entities;

import java.time.LocalDateTime;

import com.final_project.eduflow.Data.Entities.IdClasses.StaffCommentsId;

import jakarta.persistence.*;


@Entity
@Table(name = "staff_comments")
@IdClass(StaffCommentsId.class)
public class StaffComments {
    
    @Id
    @Column(name = "requester_id")
    private int requesterId;

    @Id
    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Id
    @Column(name = "request_type_id")
    private int requestTypeId;

    @Id
    @Column(name = "staff_id")
    private int staffId;

    @Column(name = "comment")
    private String comment;

    public StaffComments(int requesterId, LocalDateTime requestDate, int requestTypeId, int staffId, String comment) {
        this.requesterId = requesterId;
        this.requestDate = requestDate;
        this.requestTypeId = requestTypeId;
        this.staffId = staffId;
        this.comment = comment;
    }

    public StaffComments() {
    }

    // getters and setters

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
}
