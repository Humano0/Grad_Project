package com.final_project.eduflow.Data.View;

import javax.persistence.*;

@Entity
@Table(name = "courseBranchStudentList")
public class CourseBranchStudents {

    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "branch_number")
    private Integer branchNumber;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    // Getters and Setters

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(Integer branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}