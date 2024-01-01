package com.final_project.datalayer.View;

import javax.persistence.*;

@Entity
@Table(name = "studentRequestPath")
public class StudentRequestPath {

    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "adviser_name")
    private String adviserName;

    @Column(name = "adviser_id")
    private Integer adviserId;

    @Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "actor_name")
    private String actorName;

    @Column(name = "actor_index")
    private Integer actorIndex;

    // Getters and Setters

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getAdviserName() {
        return adviserName;
    }

    public void setAdviserName(String adviserName) {
        this.adviserName = adviserName;
    }

    public Integer getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(Integer adviserId) {
        this.adviserId = adviserId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Integer getActorIndex() {
        return actorIndex;
    }

    public void setActorIndex(Integer actorIndex) {
        this.actorIndex = actorIndex;
    }
}