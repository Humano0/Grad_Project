package com.final_project.eduflow.Services.Interfaces;


import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.Presentation.ResponseClasses.StaffInfoForAdmin;
import java.util.List;

public interface ITeachingStaffService {

    public List<StaffInfoForAdmin> getStaffInfoForAdmin();

    public TeachingStaff addStaff(TeachingStaff teachingStaff);

    
} 


    
