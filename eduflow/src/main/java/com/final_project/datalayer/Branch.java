package com.final_project.datalayer;

import javax.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "branch_number")
    private int branchNumber;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "instructor_id")
    private int instructorId;

    @Column(name = "assistant_id")
    private int assistantId;

    @Column(name = "quota")
    private int quota;

    public int getBranchId() {
        return branchId;
    }
    
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    
    public int getBranchNumber() {
        return branchNumber;
    }
    
    public void setBranchNumber(int branchNumber) {
        this.branchNumber = branchNumber;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public int getInstructorId() {
        return instructorId;
    }
    
    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
    
    public int getAssistantId() {
        return assistantId;
    }
    
    public void setAssistantId(int assistantId) {
        this.assistantId = assistantId;
    }
    
    public int getQuota() {
        return quota;
    }
    
    public void setQuota(int quota) {
        this.quota = quota;
    }
}