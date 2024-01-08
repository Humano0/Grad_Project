package com.final_project.eduflow.Data.Entities;

import jakarta.persistence.*;

@Entity
@javax.persistence.Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false)
    private String name;

    // getters and setters

    public Long getFacultyId() {
        return id;
    }

    public void setFacultyId(Long facultyId) {
        this.id = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}