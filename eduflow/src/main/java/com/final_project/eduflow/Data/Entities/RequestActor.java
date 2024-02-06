package com.final_project.eduflow.Data.Entities;

import com.final_project.eduflow.Data.Entities.IdClasses.RequestActorId;

import jakarta.persistence.*;

@Entity
@Table(name = "request_actors")
@IdClass(RequestActorId.class)
public class RequestActor {


    @Id
    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Id
    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "index")
    private Integer index;

    // Getters and Setters

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}