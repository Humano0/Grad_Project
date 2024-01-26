package com.final_project.eduflow.DataAccess;

import java.util.List;

import com.final_project.eduflow.Data.View.StudentRequestsListingView;
import org.springframework.data.jpa.repository.JpaRepository;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.Entities.StudentRequestsId;
import org.springframework.data.jpa.repository.Query;

public interface StudentRequestRepository  extends JpaRepository<StudentRequests, Long>{
    List<StudentRequests> findByStudentId(long studentId);
}