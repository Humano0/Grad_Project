package com.final_project.eduflow.Data.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StudentRequestsId implements Serializable {
    private long studentId;
    private int requestTypeId;
    private LocalDateTime when;

    // getters and setters
}