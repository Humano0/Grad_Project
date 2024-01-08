package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.TeachingStaff;



public interface TeachingStaffRepository extends JpaRepository<TeachingStaff, Integer>{
    
    TeachingStaff findByEmail(String email);

    TeachingStaff findById(Long id);

    TeachingStaff findByEmailAndPassword( String email, String password);
}
