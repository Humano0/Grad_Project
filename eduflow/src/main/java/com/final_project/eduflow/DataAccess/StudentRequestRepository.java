package com.final_project.eduflow.DataAccess;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.final_project.eduflow.Data.Entities.StudentRequests;

public interface StudentRequestRepository  extends JpaRepository<StudentRequests, Long>{
    List<StudentRequests> findByStudentId(long studentId);
    Optional<StudentRequests> findByStudentIdAndRequestTypeIdAndWhen(long studentId, long requestTypeId, OffsetDateTime when);
}