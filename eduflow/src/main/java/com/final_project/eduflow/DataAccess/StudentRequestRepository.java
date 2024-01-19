package com.final_project.eduflow.DataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.Entities.StudentRequestsId;

public interface StudentRequestRepository  extends JpaRepository<StudentRequests, StudentRequestsId>{


    List<StudentRequests> findByStudentId(long studentId);


} 