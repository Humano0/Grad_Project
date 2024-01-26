package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.View.RequestRequirementView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RequestRequirementViewRepository extends JpaRepository<RequestRequirementView, Long> {
    List<RequestRequirementView> findByRequestId(long requestId);
}
