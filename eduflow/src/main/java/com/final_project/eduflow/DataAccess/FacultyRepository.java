package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.Department;
import com.final_project.eduflow.Data.Entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findById(Integer facultyId);
    Faculty findByName(String name);
}
