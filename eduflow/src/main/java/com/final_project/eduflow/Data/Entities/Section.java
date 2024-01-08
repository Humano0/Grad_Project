package com.final_project.eduflow.Data.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "section")
public class Section {

    @Id
    @Column(name = "section_number")
    private int sectionNumber;

    @Id
    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "instructor_id")
    private Integer instructorId;

    @Column(name = "assistant_id")
    private Integer assistantId;

    // getters and setters
    public int getSectionNumber() {
        return sectionNumber;
    }
    
    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public Integer getInstructorId() {
        return instructorId;
    }
    
    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }
    
    public Integer getAssistantId() {
        return assistantId;
    }
    
    public void setAssistantId(Integer assistantId) {
        this.assistantId = assistantId;
    }
}