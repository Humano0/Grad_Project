package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.RequestActor;
import com.final_project.eduflow.Data.Entities.IdClasses.RequestActorId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface RequestActorRepository extends JpaRepository<RequestActor, RequestActorId> {
    Optional<RequestActor> findByRequestTypeIdAndIndex(Long requestTypeId, int index);
    List <RequestActor> findByRequestTypeId(Long requestTypeId);
    RequestActor findByRequestTypeIdAndStaffId(Long requestTypeId, Long staffId);
    Optional<RequestActor> findByRequestTypeIdAndStaffIdAndIndex(Long requestTypeId, Long staffId, int index);
    void deleteByRequestTypeId(Long requestTypeId);
}
