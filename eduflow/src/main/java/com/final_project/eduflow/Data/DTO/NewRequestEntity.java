package com.final_project.eduflow.Data.DTO;

import java.time.LocalDateTime;

public class NewRequestEntity {
    
    private long studentId;

    private long requestTypeId;

    private String information;

    private String addition;

    private int currentIndex;

    public long getStudentId() {
        return studentId;
    }
    
    public long getRequestTypeId() {
        return requestTypeId;
    }
    
    public String getInformation() {
        return information;
    }
    
    public String getAddition() {
        return addition;
    }
    
    public int getCurrentIndex() {
        return currentIndex;
    }
}
