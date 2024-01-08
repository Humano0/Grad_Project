package com.final_project.datalayer.Dto;

public class User {
    
    private String name;

    private String email;

    private String password;

    private String role;

    private int Id;

    private String phone;

    private String token;

    public User(String name, String email, String password, String role, int id, String phone, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.Id = id;
        this.phone = phone;
        this.token = token;
    }

    public User(String name, String email, String password, String role, int id, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.Id = id;
        this.token = token;
    }

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
    public int getId(){
        return this.Id;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getToken(){
        return this.token;
    }
}
