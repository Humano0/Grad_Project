package com.final_project.datalayer;

import javax.persistence.*;

@Entity
@Table(name = "teaching_staff")
public class TeachingStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "staff_role")
    private String staffRole;

    @Column(name = "isDean")
    private boolean isDean;

    @Column(name = "isViceDean")
    private boolean isViceDean;

    @Column(name = "isDepartmentHead")
    private boolean isDepartmentHead;

    @Column(name = "isViceDepartmentHead")
    private boolean isViceDepartmentHead;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getStaffName() {
        return staffName;
    }
    
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getStaffRole() {
        return staffRole;
    }
    
    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }
    
    public boolean isDean() {
        return isDean;
    }
    
    public void setDean(boolean dean) {
        isDean = dean;
    }
    
    public boolean isViceDean() {
        return isViceDean;
    }
    
    public void setViceDean(boolean viceDean) {
        isViceDean = viceDean;
    }
    
    public boolean isDepartmentHead() {
        return isDepartmentHead;
    }
    
    public void setDepartmentHead(boolean departmentHead) {
        isDepartmentHead = departmentHead;
    }
    
    public boolean isViceDepartmentHead() {
        return isViceDepartmentHead;
    }
    
    public void setViceDepartmentHead(boolean viceDepartmentHead) {
        isViceDepartmentHead = viceDepartmentHead;
    }
}