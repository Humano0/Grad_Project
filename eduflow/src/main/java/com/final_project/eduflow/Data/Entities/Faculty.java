package com.final_project.eduflow.Data.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false)
    private String name;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long facultyId) {
        this.id = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}