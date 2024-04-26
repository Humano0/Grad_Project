package com.final_project.eduflow.Services;

import org.springframework.stereotype.Service;


import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Services.Interfaces.ITeachingStaffService;
import com.final_project.eduflow.Presentation.ResponseClasses.StaffInfoForAdmin;

import com.final_project.eduflow.Data.Entities.TeachingStaff;


import java.util.*;


@Service
public class TeachingStaffService implements ITeachingStaffService{
    
    private final TeachingStaffRepository teachingStaffRepository;

    public TeachingStaffService(TeachingStaffRepository teachingStaffRepository) {
        this.teachingStaffRepository = teachingStaffRepository;
    }

    public boolean isDuplicateRole(long departmentId, String role) {
        if (role == "Faculty"){
            return teachingStaffRepository.existsByRole(role);
        }else{
            return teachingStaffRepository.existsByDepartmentIdAndRole((int)departmentId, role);
        }
    }



    //TODO make this method work with DTO
    public List<StaffInfoForAdmin> getStaffInfoForAdmin() {
        List<Object[]> staff = teachingStaffRepository.findAllWithDepartments();
        //List<StaffWithDepartmentsDTO> staff = teachingStaffRepository.findAllWithDepartments();
        List<StaffInfoForAdmin> staffInfoForAdmin = new ArrayList<>();

        for (Object[] teachingStaff : staff) {
            staffInfoForAdmin.add(new StaffInfoForAdmin((Integer)teachingStaff[0], (String)teachingStaff[1], (String)teachingStaff[2] + " " + (String)teachingStaff[3], (String)teachingStaff[5], (String)teachingStaff[6]));
            //staffInfoForAdmin.add(new StaffInfoForAdmin(teachingStaff.getId(), teachingStaff.getEmail(), teachingStaff.getFullName(), teachingStaff.getRole(), teachingStaff.getDepartment()));
        }
        return staffInfoForAdmin;
    }

    public TeachingStaff addStaff(TeachingStaff newTeachingStaff) {
        if (newTeachingStaff.getRole() == "Department" || newTeachingStaff.getRole() == "Faculty") {
            if (isDuplicateRole(newTeachingStaff.getDepartmentId(), newTeachingStaff.getRole() )) {
                return null;
            }
        }
        var result= teachingStaffRepository.save(newTeachingStaff);
        return result;
    }

    public TeachingStaff updateStaff(TeachingStaff teachingStaff) {
        if (teachingStaff.getRole() == "Department" || teachingStaff.getRole() == "Faculty") {
            if (isDuplicateRole(teachingStaff.getDepartmentId(), teachingStaff.getRole() )) {
                return null;
            }
        }
        var result= teachingStaffRepository.save(teachingStaff);
        return result;
    }
}
