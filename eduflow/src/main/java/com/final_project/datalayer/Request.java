package com.final_project.datalayer;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @Column(name = "student_adviser_id")
    private Integer studentAdviserId;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "request_type_id")
    private Integer requestTypeId;

    @Column(name = "information")
    private String information;

    @Column(name = "addition")// this addition is for the additional files for some request types it can be null like pdf, docx, etc.
    private String addition;

    @Column(name = "current_index")
    private Integer currentIndex;

    // Getters and Setters

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Integer getStudentAdviserId() {
        return studentAdviserId;
    }

    public void setStudentAdviserId(Integer studentAdviserId) {
        this.studentAdviserId = studentAdviserId;
    }

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