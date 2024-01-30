package com.final_project.eduflow.Data.View;

import java.time.LocalDateTime;

import org.checkerframework.checker.units.qual.C;
import org.springframework.cglib.core.Local;

import com.final_project.eduflow.Data.View.IdClasses.WaitinRequestsViewId;

import jakarta.persistence.*;

@Entity
@Table(name = "waiting_requests_unioned_view")
@IdClass(WaitinRequestsViewId.class)
public class WaitingRequestView {
    
    @Id
    @Column(name = "student_id")
    private long studentId;

    @Column(name = "request_type_id")
    private int requestTypeId;

    @Column(name = "request_type_name")
    private String requestTypeName;

    @Column(name = "current_index")
    private long current_index;

    @Column(name = "information")
    private String information;

    @Id
    @Column(name = "when_created")
    private LocalDateTime whenCreated;

    @Column(name = "student_comment")
    private String studentComment;

    @Id
    @Column(name = "current_actor_id")
    private long currentActorId;

    // getters and setters
    
    public long getStudentId() {
        return studentId;
    }

    public int getRequestTypeId() {
        return requestTypeId;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public long getCurrent_index() {
        return current_index;
    }

    public String getInformation() {
        return information;
    }

    public LocalDateTime getWhenCreated() {
        return whenCreated;
    }

    public String getStudentComment() {
        return studentComment;
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

    public void setCurrent_index(long current_index) {
        this.current_index = current_index;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setWhenCreated(LocalDateTime whenCreated) {
        this.whenCreated = whenCreated;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public void setCurrentActorId(long currentActorId) {
        this.currentActorId = currentActorId;
    }

    
}
