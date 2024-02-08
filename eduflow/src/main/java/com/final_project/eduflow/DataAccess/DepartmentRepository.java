package com.final_project.eduflow.DataAccess;


import com.final_project.eduflow.Data.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long > {
    Department findByFacultyId(Integer facultyId);
    Department findByName(String name);

}
