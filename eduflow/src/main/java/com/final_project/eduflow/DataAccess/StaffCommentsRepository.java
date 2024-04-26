package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.final_project.eduflow.Data.Entities.StaffComments;
import com.final_project.eduflow.Data.Entities.IdClasses.StaffCommentsId;

import java.time.OffsetDateTime;
import java.util.List;

public interface StaffCommentsRepository extends JpaRepository<StaffComments, StaffCommentsId> {
    List<StaffComments> findByRequestWhenCreatedAndRequestStudentIdAndRequestTypeId(OffsetDateTime requestWhenCreated, Long requestStudentId, Long requestTypeId);
}