package com.final_project.eduflow.Data.DTO;

public class RequestTypesEntity {
    private String requestName;

    public RequestTypesEntity(String requestName) {
        this.requestName = requestName;
    }

    public RequestTypesEntity() {
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
