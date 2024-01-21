package com.final_project.eduflow.DataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.View.RequestRequirementView;

public interface RequestRequirementRepository  extends JpaRepository<RequestRequirementView, Long>{

    List<RequestRequirementView> findByRequestId(int requestid);
}