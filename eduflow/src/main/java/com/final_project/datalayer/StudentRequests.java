package com.final_project.datalayer;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_requests")
public class StudentRequests {

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Id
    @Column(name = "request_type_id")
    private int requestTypeId;

    @Id
    @Column(name = "when")
    private LocalDateTime when;

    @Column(name = "information")
    private String information;

    @Column(name = "addition")
    private String addition;

    @Column(name = "current_index")
    private Integer currentIndex;

    // getters and setters

    public Integer getStudentId() {
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
