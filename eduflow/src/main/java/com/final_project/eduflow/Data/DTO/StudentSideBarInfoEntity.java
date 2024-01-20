package com.final_project.eduflow.Data.DTO;

public class StudentSideBarInfoEntity {
    private String firstname;
    private String lastname;
    private long id;

    public StudentSideBarInfoEntity(String firstname, String lastname, long id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    public StudentSideBarInfoEntity() {
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public long getId() {
        return id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(long id) {
        this.id = id;
    }
}
