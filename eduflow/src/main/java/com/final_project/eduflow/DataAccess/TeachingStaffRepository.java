package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.final_project.eduflow.Data.TeachingStaff;



public interface StaffRepository extends CrudRepository<TeachingStaff, Integer>{
    
    TeachingStaff findByEmail(String email);
}
