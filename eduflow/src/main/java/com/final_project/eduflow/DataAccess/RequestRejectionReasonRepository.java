package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.Entities.RequestRejectionReason;

public interface RequestRejectionReasonRepository extends JpaRepository<RequestRejectionReason, String>{
    
    RequestRejectionReason findByRequestUUID(String requestUUID);
}
