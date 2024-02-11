package com.final_project.eduflow.DataAccess;


import com.final_project.eduflow.Data.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long > {
    List<Department> findByFacultyId(Long facultyId);
    Department findByName(String name);
}
