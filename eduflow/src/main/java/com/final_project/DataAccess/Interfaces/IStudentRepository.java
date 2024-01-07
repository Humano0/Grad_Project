package com.final_project.DataAccess.Interfaces;

import java.util.List;

import com.final_project.datalayer.Student;

public interface IStudentRepository {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(Long id);
    Student getStudentById(Long id);
    Student getStudentByEmail(String email);
    List<Student> getStudentsByDepartmentId(Long departmentId);
    List<Student> getStudentsByAdviserId(Long adviserId);

    // TODO: section, section_records and course tables attributes are weirdly named.(?);
    //List<Student> getStudentsBySectionId(Long courseId);
}
