package com.final_project.eduflow.Services.Interfaces;

import com.final_project.eduflow.Data.DTO.AdvisorDashboardInfoEntity;

public interface IAdvisorInfoService {
    AdvisorDashboardInfoEntity getAdvisorDashboardInfoByStudentId(Long id);
}
