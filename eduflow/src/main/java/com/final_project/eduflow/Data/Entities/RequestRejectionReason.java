package com.final_project.eduflow.Data.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cancel_rejection_reason")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRejectionReason {
    
    @Id
    @Column(name = "request_uuid")
    private String requestUUID;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status;


}
