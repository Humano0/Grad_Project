package com.final_project.datalayer.Dto;

public class User {
    
    private String name;

    private String email;

    private String password;

    private String role;

    private int studentId;

    private String phone;

    private String token;

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){
        return this.role;
    }
    public int getStudentId(){
        return this.studentId;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getToken(){
        return this.token;
    }
}
