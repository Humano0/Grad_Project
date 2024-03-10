package com.final_project.eduflow.Data.Entities.IdClasses;

import java.io.Serializable;
import java.time.*;

public class StudentRequestsId {
    private long studentId;
    private long requestTypeId;
    private OffsetDateTime when;

    // getters and setters

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public OffsetDateTime getWhen() {
        return when;
    }

    public void setWhen(OffsetDateTime when) {
        this.when = when;
    }
}