package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.View.StudentRequestsListingView;
import com.final_project.eduflow.Data.View.IdClasses.StudentRequestsListingViewId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRequestsListingViewRepository extends JpaRepository<StudentRequestsListingView, StudentRequestsListingViewId> {
    List<StudentRequestsListingView> findStudentRequestsListingViewByStudentId(Long studentId);

}
