package com.final_project.eduflow.Data.Entities;

import com.final_project.eduflow.Data.Entities.IdClasses.RequestRequirementId;

import jakarta.persistence.*;

@Entity
@Table(name = "request_requirements")
@IdClass(RequestRequirementId.class)
public class RequestRequirement {


    @Id
    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Id
    @Column(name = "index")
    private int index;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "pretext")
    private String pretext;


    public RequestRequirement() {
    }

    // Getters and Setters



    public String getPretext() {
        return pretext;
    }

    public void setPretext(String pretext) {
        this.pretext = pretext;
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

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    
}