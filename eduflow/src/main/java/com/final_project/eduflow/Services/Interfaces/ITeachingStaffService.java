package com.final_project.eduflow.Services.Interfaces;

import com.final_project.eduflow.DataAccess.DepartmentRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Presentation.ResponseClasses.StaffInfoForAdmin;
import java.util.List;

public interface ITeachingStaffService {

    public List<StaffInfoForAdmin> getStaffInfoForAdmin();
    
} 


    
