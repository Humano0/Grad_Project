package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.Entities.RequestRejectionReason;

import java.util.UUID;

public interface RequestRejectionReasonRepository extends JpaRepository<RequestRejectionReason, String>{
    
    RequestRejectionReason findByRequestUUID(UUID requestUUID);
}
