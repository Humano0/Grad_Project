package com.final_project.eduflow.Data.DTO;

public class RequestTypesEntity {
    private String requestName;
    private long id;

    public RequestTypesEntity(String requestName, long id) {
        this.requestName = requestName;
        this.id = id;
    }

    public RequestTypesEntity() {
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
