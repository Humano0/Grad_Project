package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String > {
    Course findByCode(String code);
    Course findByDepartmentId(Integer departmentId);
    Course findByType(String type);
    Course findByName(String name);
}
