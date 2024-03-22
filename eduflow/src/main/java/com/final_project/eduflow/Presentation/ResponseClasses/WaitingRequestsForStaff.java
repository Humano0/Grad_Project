package com.final_project.eduflow.Presentation.ResponseClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class WaitingRequestsForStaff {
    private long studentId;
    private String studentName;
    private String studentMail;
    private String studentDepartment;
    private int requestTypeId;
    private String requestTypeName;
    private int current_index;
    private String information;
    private LocalDateTime whenCreated;
    private long currentActorId;
    private String status;
    // getters and setters

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }
    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getStudentMail() {
        return studentMail;
    }
    public String getStudentDepartment() {
        return studentDepartment;
    }
    public long getStudentId() {
        return studentId;
    }

    public int getRequestTypeId() {
        return requestTypeId;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public int getCurrent_index() {
        return current_index;
    }

    public String getInformation() {
        return information;
    }

    public LocalDateTime getWhenCreated() {
        return whenCreated;
    }

    public long getCurrentActorId() {
        return currentActorId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setRequestTypeId(int requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public void setCurrent_index(int current_index) {
        this.current_index = current_index;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setWhenCreated(LocalDateTime whenCreated) {
        this.whenCreated = whenCreated;
    }

    public void setCurrentActorId(long currentActorId) {
        this.currentActorId = currentActorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
