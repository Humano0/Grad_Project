package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>{

    Student findByEmail(String email);

    Optional<Student> findById(Long id);

    Student findByEmailAndPassword( String email, String password);
} 