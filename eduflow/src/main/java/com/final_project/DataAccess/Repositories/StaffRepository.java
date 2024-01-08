package com.final_project.DataAccess.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.datalayer.TeachingStaff;

public interface StaffRepository extends JpaRepository<TeachingStaff, Integer>{
    
    TeachingStaff findByEmail(String email);
}
