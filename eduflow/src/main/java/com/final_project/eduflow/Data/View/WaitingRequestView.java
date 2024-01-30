package com.final_project.eduflow.Data.View;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import com.final_project.eduflow.Data.View.IdClasses.WaitinRequestsViewId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "waiting_requests_unioned_view")
@IdClass(WaitinRequestsViewId.class)
public class WaitingRequestView {
    
    @Id
    private long studentId;

    private long current_index;

    private String information;

    @Id
    private LocalDateTime whenCreated;

    private String studentComment;

    @Id
    private long currentActorId;
}
