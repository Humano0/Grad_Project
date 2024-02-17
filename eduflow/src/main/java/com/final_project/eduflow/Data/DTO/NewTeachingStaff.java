package com.final_project.eduflow.Data.DTO;

public class NewTeachingStaff {
    
    private String name;
    private String email;
    private String password;
    private long departmentId;
    private String role;
    private String phone;
    private String web;

    public NewTeachingStaff(String name, String email, String password, long departmentId, String role, String phone, String web) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.departmentId = departmentId;
        this.role = role;
        this.phone = phone;
        this.web = web;
    }

    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getWeb() {
        return web;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartmentId(long department) {
        this.departmentId = department;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWeb(String web) {
        this.web = web;
    }



}
