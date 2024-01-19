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
    private Integer departmentId;

    @Column(name = "adviser_id")
    private Integer adviserId;

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
    
    public Integer getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    
    public Integer getAdviserId() {
        return adviserId;
    }
    
    public void setAdviserId(Integer adviserId) {
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
        return adviserId.longValue();
    }
}