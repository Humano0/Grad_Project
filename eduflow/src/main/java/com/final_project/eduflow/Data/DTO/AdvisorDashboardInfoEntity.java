package com.final_project.eduflow.Data.DTO;

public class AdvisorDashboardInfoEntity {
    private String firstname;
    private String lastname;
    private String department;

    private String web;

    private String phoneNumber;

    public AdvisorDashboardInfoEntity(String firstname, String lastname, String department, String web, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.web = web;
        this.phoneNumber = phoneNumber;
    }

    public AdvisorDashboardInfoEntity() {
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWeb() { return web; }

    public void setWeb(String web) { this.web = web; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
