package com.final_project.datalayer;

import javax.persistence.*;

@Entity
@Table(name = "section_records")
public class SectionRecords {

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Id
    @Column(name = "course_code")
    private String courseCode;

    @Id
    @Column(name = "section_number")
    private int sectionNumber;

    @Id
    @Column(name = "term")
    private String term;

    // getters and setters

    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public int getSectionNumber() {
        return sectionNumber;
    }
    
    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }
    
    public String getTerm() {
        return term;
    }
    
    public void setTerm(String term) {
        this.term = term;
    }
}