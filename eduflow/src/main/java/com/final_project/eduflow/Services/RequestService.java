package com.final_project.eduflow.Services;

import com.final_project.eduflow.Data.DTO.ListRequestsEntity;
import com.final_project.eduflow.Data.View.StudentRequestsListingView;
import com.final_project.eduflow.DataAccess.StudentRequestRepository;
import com.final_project.eduflow.DataAccess.StudentRequestsListingViewRepository;
import com.final_project.eduflow.Services.Interfaces.IRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService implements IRequestService {

    private final StudentRequestsListingViewRepository studentRequestsListingViewRepository;

    public RequestService(StudentRequestsListingViewRepository studentRequestsListingViewRepository) {
        this.studentRequestsListingViewRepository = studentRequestsListingViewRepository;
    }
    @Override
    public List<StudentRequestsListingView> getStudentRequestsById(Long id) {
        return studentRequestsListingViewRepository.findStudentRequestsListingViewByStudentId(id);
    }
}
