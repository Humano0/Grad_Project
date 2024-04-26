package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.RequestActor;
import com.final_project.eduflow.Data.Entities.IdClasses.RequestActorId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface RequestActorRepository extends JpaRepository<RequestActor, RequestActorId> {

/*     @Query(value = "DELETE FROM request_actors WHERE requestTypeId = :requestTypeId", nativeQuery = true)
    @Modifying
    void deleteByRequestTypeId(@Param("requestTypeId") Long requestTypeId); */

    void deleteByRequestTypeId(Long requestTypeId);
    Optional<RequestActor> findByRequestTypeIdAndIndex(Long requestTypeId, int index);
    List <RequestActor> findByRequestTypeId(Long requestTypeId);
    RequestActor findByRequestTypeIdAndStaffId(Long requestTypeId, Long staffId);
    Optional<RequestActor> findByRequestTypeIdAndStaffIdAndIndex(Long requestTypeId, Long staffId, int index);
//    Optional <RequestActor> findByRequestTypeIdAndIndex(long requestTypeId, int index);
}
