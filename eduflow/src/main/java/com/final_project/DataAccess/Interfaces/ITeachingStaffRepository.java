package com.final_project.DataAccess.Interfaces;

import com.final_project.datalayer.TeachingStaff;

import java.util.List;

public interface ITeachingStaffRepository {
    void addTeachingStaff(TeachingStaff teachingStaff);
    TeachingStaff getTeachingStaffByEmail(String email);
    TeachingStaff getTeachingStaffById(Long id);
    List<TeachingStaff> getTeachingStaffByDepartmentId(Long departmentId);
    TeachingStaff getTeachingStaffByStudentId(Long studentId);
}