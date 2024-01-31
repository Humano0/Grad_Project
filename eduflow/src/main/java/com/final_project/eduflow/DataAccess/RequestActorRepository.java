package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.RequestActor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestActorRepository extends JpaRepository<RequestActor, Long> {
    Optional<RequestActor> findByRequestTypeIdAndIndex(Long requestTypeId, Integer index);
}
