package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.final_project.eduflow.Data.Entities.TeachingStaff;

public interface TeachingStaffRepository extends JpaRepository<TeachingStaff, Long>{
    TeachingStaff findByEmail(String email);
    TeachingStaff findByEmailAndPassword( String email, String password);
}