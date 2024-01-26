package com.final_project.eduflow.Data.View;

import com.final_project.eduflow.Data.View.IdClasses.StudentRequestsListingViewId;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.sql.Timestamp;

@Entity
@Immutable
@Table(name = "student_requests_listing_view")
@IdClass(StudentRequestsListingViewId.class)
public class StudentRequestsListingView {

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "current_index")
    private Integer currentIndex;

    @Column(name = "information")
    private String information;

    @Column(name = "status")
    private String status;

    @Id
    @Column(name = "when")
    private Timestamp when;

    // Getters and Setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
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

    public Timestamp getWhen() {
        return when;
    }

    public void setWhen(Timestamp when) {
        this.when = when;
    }
}