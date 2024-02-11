package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.View.WaitingRequestView;
import com.final_project.eduflow.Data.View.IdClasses.WaitinRequestsViewId;

import java.util.List;

public interface WaitingRequestsViewRepository extends JpaRepository<WaitingRequestView, WaitinRequestsViewId>{
    List<WaitingRequestView> findByStudentIdAndCurrentActorId(Long studentId, Long currentActorId);
    List<WaitingRequestView> findByCurrentActorId(Long currentActorId);
    List<WaitingRequestView> findByStudentId(Long studentId);
}