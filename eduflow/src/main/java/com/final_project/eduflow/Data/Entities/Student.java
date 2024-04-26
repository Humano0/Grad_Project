package com.final_project.eduflow.Data.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "adviser_id")
    private Long adviserId;

    @Column(name = "password")
    private String password;

    // getters and setters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getFullName(){

        return name + " " + surname;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    
    public Long getAdviserId() {
        return adviserId;
    }
    
    public void setAdviserId(Long adviserId) {
        this.adviserId = adviserId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public long getId(){
        return id;
    }

    public Long getAdvisorId() {
        return adviserId;
    }

    public String getFirstname() {
        return name;
    }

    public String getLastname() {
        return surname;
    }
}