package com.final_project.eduflow.Data.Entities;
import java.io.Serializable;

public class SectionId implements Serializable {
    private int sectionNumber;
    private String courseCode;

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
}