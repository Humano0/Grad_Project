package com.final_project.eduflow.Data.View;

import com.final_project.eduflow.Data.View.IdClasses.RequestRequirmentViewId;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "requestrequirements")
@IdClass(RequestRequirmentViewId.class)
public class RequestRequirementView {

    @Id
    @Column(name = "requestid")
    private Long requestId;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Id
    @Column(name = "index")
    private Integer index;

    // Getters

    public Long getRequestId() {
        return requestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getIndex() {
        return index;
    }
}
