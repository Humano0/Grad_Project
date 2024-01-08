package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.Student;

public interface StudentRepositoy extends JpaRepository<Student, Long>{

    
} 