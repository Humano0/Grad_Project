package com.final_project.eduflow.Data.View;


import com.final_project.eduflow.Data.View.IdClasses.ConcludedRequestsId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "concluded_requests_with_actors_view")
@IdClass(ConcludedRequestsId.class)
public class ConcludedRequestView {
    
    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Id
    @Column(name = "when_created")
    private LocalDateTime whenCreated;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "email")
    private String studentEmail;

    @Column(name = "advisor")
    private String advisor;

    @Column(name = "department_name")
    private String studentDepartment;

    @Id
    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Column(name = "request_name")
    private String requestTypeName;

    @Column(name = "current_index")
    private int currentIndex;

    @Column(name = "actor_id")
    private long actorId;

    @Column(name = "information")
    private String information;

    @Column(name = "status")
    private String status;

    @Column(name = "addition")
    private String addition;
    // getters and setters

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public LocalDateTime getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(LocalDateTime whenCreated) {
        this.whenCreated = whenCreated;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
    }
     
    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }
}
