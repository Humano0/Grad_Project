package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.View.AdvisorInfoView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorInfoViewRepository extends JpaRepository<AdvisorInfoView, Long> {
    AdvisorInfoView findByStudentId(long studentId);
}
