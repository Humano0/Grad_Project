package com.final_project.eduflow.Data.View.IdClasses;


import java.io.Serializable;
import java.sql.Timestamp;

public class StudentRequestsListingViewId implements Serializable {

    private Long studentId;
    private Timestamp when;

    // Getters and Setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Timestamp getWhen() {
        return when;
    }

    public void setWhen(Timestamp when) {
        this.when = when;
    }
}