package com.final_project.DataAccess.Interfaces;

import java.util.List;

import com.final_project.datalayer.Student;

public interface IStudentRepository extends IGeneralRepository<Student> {
    Student getByEmail(String email);
    List<Student> getStudentsByDepartmentId(Long departmentId);
    List<Student> getStudentsByAdviserId(Long adviserId);
}
