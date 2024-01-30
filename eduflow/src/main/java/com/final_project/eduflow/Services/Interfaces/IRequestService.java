package com.final_project.eduflow.Services.Interfaces;

import com.final_project.eduflow.Data.DTO.ListRequestsEntity;
import com.final_project.eduflow.Data.View.StudentRequestsListingView;

import java.util.List;

public interface IRequestService {
    List<StudentRequestsListingView> getStudentRequestsById(Long id);
    
}
