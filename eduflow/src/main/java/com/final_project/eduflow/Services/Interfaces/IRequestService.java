package com.final_project.eduflow.Services.Interfaces;


import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.View.StudentRequestsListingView;

import com.final_project.eduflow.Presentation.ResponseClasses.ListRequestTypes;


import java.util.List;

public interface IRequestService {
    List<StudentRequestsListingView> getStudentRequestsById(Long id);
    Long acceptRequest(StudentRequests studentRequests);
    void rejectRequest(StudentRequests studentRequests);
    boolean checkIfNextActorIsTheOneAcceptingTheRequest(Long staffId, Long request_type_id, Integer current_index);
    boolean checkIfRequestActorIsTrue(Long staffId, Long requestTypeId, int index);
    List<ListRequestTypes> getAllRequestTypes();
    List<ListRequestTypes> getAllRequestTypesByDepartmentId(Long departmentId);
}
