package com.final_project.eduflow.Data.DTO;

public class User {
    
    private String name;

    private String email;

    private String password;

    private String role;

    private long Id;

    private String phone;

    private String token;

    public User(String name, String email, String password, String role, long id, String phone, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.Id = id;
        this.phone = phone;
        this.token = token;
    }

    public User(String name, String email, String password, String role, long id, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.Id = id;
        this.token = token;
    }

    public User(String name, String email, String password, String role, long id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.Id = id;
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
    public long getId(){
        return this.Id;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getToken(){
        return this.token;
    }
}
