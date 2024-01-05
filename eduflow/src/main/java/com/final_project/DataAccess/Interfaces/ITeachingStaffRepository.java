package com.final_project.DataAccess.Interfaces;

import com.final_project.datalayer.TeachingStaff;

public interface ITeachingStaffRepository extends IGeneralRepository<TeachingStaff> {
    void addTeachingStaff(TeachingStaff teachingStaff);
    TeachingStaff getTeachingStaffByStudentId(Long studentId);
}