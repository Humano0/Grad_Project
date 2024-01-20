package com.final_project.eduflow.Data.DTO;

public class AdvisorDashboardInfoEntity {
    private String firstname;
    private String lastname;
    private String department;

    public AdvisorDashboardInfoEntity(String firstname, String lastname, String department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
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
}
