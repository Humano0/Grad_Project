package com.final_project.eduflow.Services;

import com.final_project.eduflow.Data.DTO.AdvisorDashboardInfoEntity;
import com.final_project.eduflow.DataAccess.AdvisorInfoViewRepository;
import com.final_project.eduflow.Services.Interfaces.IAdvisorInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdvisorInfoService implements IAdvisorInfoService {
    private final AdvisorInfoViewRepository advisorInfoViewRepository;

    public AdvisorInfoService(AdvisorInfoViewRepository advisorInfoViewRepository) {
        this.advisorInfoViewRepository = advisorInfoViewRepository;
    }

    @Override
    public AdvisorDashboardInfoEntity getAdvisorDashboardInfoByStudentId(Long id) {
        return advisorInfoViewRepository.findById(id)
                .map(advisorInfo -> {
                    AdvisorDashboardInfoEntity advisor = new AdvisorDashboardInfoEntity();
                    advisor.setFirstname(advisorInfo.getAdvisorFirstname());
                    advisor.setLastname(advisorInfo.getAdvisorLastname());
                    advisor.setDepartment(advisorInfo.getDepartmentName());
                    advisor.setWeb(advisorInfo.getAdvisorWeb());
                    advisor.setPhoneNumber(advisorInfo.getAdvisorPhoneNumber());
                    return advisor;
                })
                .orElse(null);
    }
}
