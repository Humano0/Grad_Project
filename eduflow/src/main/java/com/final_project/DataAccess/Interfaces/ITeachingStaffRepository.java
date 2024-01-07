package com.final_project.DataAccess.Interfaces;

import com.final_project.datalayer.TeachingStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeachingStaffRepository {
    void addTeachingStaff(TeachingStaff teachingStaff);
    void updateTeachingStaff(TeachingStaff teachingStaff);
    void deleteTeachingStaffById(Long id);
    TeachingStaff getTeachingStaffByEmail(String email);
    TeachingStaff getTeachingStaffById(Long id);
    TeachingStaff getTeachingStaffByStudentId(Long studentId);
    List<TeachingStaff> getTeachingStaffByDepartmentId(Long departmentId);
}