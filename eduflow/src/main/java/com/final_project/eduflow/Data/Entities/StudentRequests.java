package com.final_project.eduflow.Data.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_requests")
@IdClass(StudentRequestsId.class)
public class StudentRequests {

    @Id
    @Column(name = "student_id")
    private long studentId;

    @Id
    @Column(name = "request_type_id")
    private int requestTypeId;

    @Id
    @Column(name = "\"when\"")
    private LocalDateTime when;

    @Column(name = "information")
    private String information;

    @Column(name = "addition")
    private String addition;

    @Column(name = "current_index")
    private Integer currentIndex;

    public StudentRequests(long studentId, int requestTypeId, String information, String addition) {
        this.studentId = studentId;
        this.requestTypeId = requestTypeId;
        this.when = LocalDateTime.now();
        this.information = information;
        this.addition = addition;
        this.currentIndex = 0;
    }

    public StudentRequests() {
    }
    // getters and setters

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }
}

