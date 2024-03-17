package com.final_project.eduflow.Data.Entities;

import com.final_project.eduflow.Data.Entities.IdClasses.RequestStatus;
import com.final_project.eduflow.Data.Entities.IdClasses.StudentRequestsId;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "student_requests")
@IdClass(StudentRequestsId.class)
public class StudentRequests {

    @Id
    @Column(name = "student_id")
    private long studentId;

    @Id
    @Column(name = "request_type_id")
    private long requestTypeId;

    @Id
    @Column(name = "when_created")
    private OffsetDateTime when;

    @Column(name = "information")
    private String information;

    @Column(name = "addition")
    private String addition;

    @Column(name = "current_index")
    private Integer currentIndex;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus status;

    public StudentRequests(long studentId, long requestTypeId, String information, String addition) {
        this.studentId = studentId;
        this.requestTypeId = requestTypeId;
        this.when = OffsetDateTime.now();
        this.information = information;
        this.addition = addition;
        this.currentIndex = 0;
        this.status = RequestStatus.waiting;
    }

    public StudentRequests() {
    }

    // getters and setters

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public OffsetDateTime getWhen() {
        return when;
    }

    public void setWhen(OffsetDateTime when) {
        this.when = when;
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}