package com.final_project.eduflow.Data.Entities.IdClasses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class StudentRequestsId implements Serializable {
    private long studentId;
    private int requestTypeId;
    private OffsetDateTime when;

    // getters and setters
}