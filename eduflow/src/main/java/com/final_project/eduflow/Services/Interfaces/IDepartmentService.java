package com.final_project.eduflow.Services.Interfaces;

import com.final_project.eduflow.Presentation.ResponseClasses.ListDepartments;

import java.util.List;

public interface IDepartmentService {
    List<ListDepartments> getAllDepartments();
    List<ListDepartments> getAllDepartmentsByFacultyId(Long facultyId);
}
