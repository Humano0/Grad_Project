package com.final_project.datalayer;

import javax.persistence.*;

@Entity
@Table(name = "request_requirements")
public class RequestRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requiermentId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "request_type_id")
    private Integer requestTypeId;

    @Column(name = "index")
    private Integer index;

    // Getters and Setters

    public Long getRequiermentId() {
        return requiermentId;
    }

    public void setRequiermentId(Long requiermentId) {
        this.requiermentId = requiermentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}