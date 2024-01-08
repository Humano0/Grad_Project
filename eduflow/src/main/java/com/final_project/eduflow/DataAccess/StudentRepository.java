package com.final_project.eduflow.DataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

    Student findByEmail(String email);

    Optional<Student> findById(Long id);

    Student findByEmailAndPassword( String email, String password);
} 