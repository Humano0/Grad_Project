package com.final_project.eduflow.Data.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cancel_reject_reasons")
public class RequestRejectionReason {
    @Id
    @Column(name = "request_uuid")
    private UUID requestUUID;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status;

    public RequestRejectionReason() {
    }

    public RequestRejectionReason(UUID requestUUID, String reason, String status) {
        this.requestUUID = requestUUID;
        this.reason = reason;
        this.status = status;
    }

    // getters and setters

    public UUID getRequestUUID() {
        return requestUUID;
    }

    public void setRequestUUID(UUID requestUUID) {
        this.requestUUID = requestUUID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
