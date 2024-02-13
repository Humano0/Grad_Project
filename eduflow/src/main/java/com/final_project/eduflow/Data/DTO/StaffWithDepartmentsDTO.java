package com.final_project.eduflow.Data.DTO;

public class StaffWithDepartmentsDTO {
 
    private long id;
    private String email;
    private String name;
    private String surname;
    private long departmentId;
    private String role;
    private String department;


    public StaffWithDepartmentsDTO(long id, String email, String name, String surname, long departmentId, String role, String department) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.departmentId = departmentId;
        this.role = role;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
