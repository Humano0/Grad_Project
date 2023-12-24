package com.final_project.datalayer.View;

import javax.persistence.*;

@Entity
@Table(name = "requestsActors")
public class RequestActorsView {

    @Id
    @Column(name = "requestid")
    private Long requestId;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "id")
    private Integer id;

    @Column(name = "staff_name")
    private String staffName;

    // Getters

    public Long getRequestId() {
        return requestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public Integer getId() {
        return id;
    }

    public String getStaffName() {
        return staffName;
    }
}
