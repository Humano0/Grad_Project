package com.final_project.eduflow.Presentation.ResponseClasses;

public class LoginResponse {
    private String status;
    private String message;
    private String role;

    public LoginResponse(String status, String message, String role) {
        this.status = status;
        this.message = message;
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
