package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.View.StudentRequestsListingView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRequestsListingViewRepository extends JpaRepository<StudentRequestsListingView, Long> {
    List<StudentRequestsListingView> findStudentRequestsListingViewByStudentId(Long studentId);

}
