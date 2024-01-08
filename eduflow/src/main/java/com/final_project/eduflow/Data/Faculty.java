package com.final_project.eduflow.Data;

import jakarta.persistence.*;

@Entity
@javax.persistence.Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long facultyId;

    @Column(nullable = false)
    private String name;

    // getters and setters

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}