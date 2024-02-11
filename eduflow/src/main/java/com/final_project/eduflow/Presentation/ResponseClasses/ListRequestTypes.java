package com.final_project.eduflow.Presentation.ResponseClasses;

public class ListRequestTypes {
    private Long id;
    private String requestName;

    public ListRequestTypes(Long id, String requestName) {
        this.id = id;
        this.requestName = requestName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
