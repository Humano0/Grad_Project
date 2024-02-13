package com.final_project.eduflow.Presentation.ResponseClasses;

public class StaffInfoForAdmin {
    
    private long id;
    private String email;
    private String fullName;
    private String role;
    private String department;

    public StaffInfoForAdmin(long id, String email, String fullName, String role, String department) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
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
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


}
