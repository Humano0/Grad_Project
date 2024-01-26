package com.final_project.eduflow.Data.View;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "advisor_info")
public class AdvisorInfoView {

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "advisor_id")
    private Long advisorId;

    @Column(name = "advisor_firstname")
    private String advisorFirstname;

    @Column(name = "advisor_lastname")
    private String advisorLastname;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "advisor_web")
    private String advisorWeb;

    @Column(name = "advisor_phone_number")
    private String advisorPhoneNumber;

    // Getters and Setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(Long advisorId) {
        this.advisorId = advisorId;
    }

    public String getAdvisorFirstname() {
        return advisorFirstname;
    }

    public void setAdvisorFirstname(String advisorFirstname) {
        this.advisorFirstname = advisorFirstname;
    }

    public String getAdvisorLastname() {
        return advisorLastname;
    }

    public void setAdvisorLastname(String advisorLastname) {
        this.advisorLastname = advisorLastname;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAdvisorWeb() {
        return advisorWeb;
    }

    public void setAdvisorWeb(String advisorWeb) {
        this.advisorWeb = advisorWeb;
    }

    public String getAdvisorPhoneNumber() {
        return advisorPhoneNumber;
    }

    public void setAdvisorPhoneNumber(String advisorPhoneNumber) {
        this.advisorPhoneNumber = advisorPhoneNumber;
    }
}