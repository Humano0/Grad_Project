package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.View.WaitingRequestView;
import com.final_project.eduflow.Data.View.IdClasses.WaitinRequestsViewId;

public interface WaitingRequestsViewRepository extends JpaRepository<WaitingRequestView, WaitinRequestsViewId>{

    
}