package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.View.RequestRequirementView;
import com.final_project.eduflow.Data.View.RequestRequirmentViewId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRequirementViewRepository extends JpaRepository<RequestRequirementView, RequestRequirmentViewId> {
    List<RequestRequirementView> findByRequestId(long requestId);
}