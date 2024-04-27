package com.final_project.eduflow.Data.Entities;

import com.final_project.eduflow.Data.Entities.IdClasses.RequestStatus;
import com.final_project.eduflow.Data.Entities.IdClasses.StudentRequestsId;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "student_requests")
@IdClass(StudentRequestsId.class)
public class StudentRequests {

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Id
    @Column(name = "request_type_id")
    private Long requestTypeId;

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

    @Column(name = "unique_request_id")
    private String uniqueRequestId;


    public StudentRequests(Long studentId, Long requestTypeId, String information, String addition) {
        this.studentId = studentId;
        this.requestTypeId = requestTypeId;
        this.when = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.information = information;
        this.addition = addition;
        this.currentIndex = 0;
        this.status = RequestStatus.WAITING;
    }

    public StudentRequests() {
    }

    // getters and setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
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

    public String getUniqueRequestId() {
        return uniqueRequestId;
    }

    public void setUniqueRequestId(String uniqueRequestId) {
        this.uniqueRequestId = uniqueRequestId;
    }
}