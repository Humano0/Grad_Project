package com.final_project.eduflow.DataAccess;

import java.util.List;

import com.final_project.eduflow.Data.Entities.RequestRequirement;
import com.final_project.eduflow.Data.Entities.IdClasses.RequestRequirementId;

import com.final_project.eduflow.Data.View.RequestRequirementView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface RequestRequirementRepository  extends JpaRepository<RequestRequirement, RequestRequirementId> {

    void deleteByRequestTypeId(Long requestTypeId);

    List<RequestRequirement> findByRequestTypeId(Long requestTypeId);

    Optional<RequestRequirement> findByRequestTypeIdAndIndex(Long requestTypeId, int index);
    
}