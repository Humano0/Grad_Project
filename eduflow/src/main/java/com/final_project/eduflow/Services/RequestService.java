package com.final_project.eduflow.Services;


import com.final_project.eduflow.Data.Entities.IdClasses.RequestStatus;
import com.final_project.eduflow.Data.Entities.RequestActor;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.View.StudentRequestsListingView;

import com.final_project.eduflow.DataAccess.*;
import com.final_project.eduflow.Presentation.ResponseClasses.ListRequestTypes;
import com.final_project.eduflow.Services.Interfaces.IRequestService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService implements IRequestService {

    private final StudentRequestsListingViewRepository studentRequestsListingViewRepository;
    private final StudentRequestRepository studentRequestRepository;
    private final RequestActorRepository requestActorRepository;
    private final RequestTypeRepository requestTypeRepository;
    private final WaitingRequestsViewRepository waitingRequestsViewRepository;

    public RequestService(StudentRequestsListingViewRepository studentRequestsListingViewRepository,
                          StudentRequestRepository studentRequestRepository,
                          RequestActorRepository requestActorRepository, WaitingRequestsViewRepository waitingRequestsViewRepository,
                          RequestTypeRepository requestTypeRepository) {
        this.studentRequestsListingViewRepository = studentRequestsListingViewRepository;
        this.studentRequestRepository = studentRequestRepository;
        this.requestActorRepository = requestActorRepository;
        this.requestTypeRepository = requestTypeRepository;
        this.waitingRequestsViewRepository = waitingRequestsViewRepository;
    }

    @Override
    public List<StudentRequestsListingView> getStudentRequestsById(Long id) {
        return studentRequestsListingViewRepository.findStudentRequestsListingViewByStudentId(id);
    }

    // Increases the index by 1
    // returns the next actor's id
    // if all actors have accepted the request, returns the student's id
    @Override
    public Long acceptRequest(StudentRequests studentRequests) {
        StudentRequests request = studentRequestRepository.findByStudentIdAndRequestTypeIdAndWhen(
                studentRequests.getStudentId(),
                studentRequests.getRequestTypeId(),
                studentRequests.getWhen()
        ).orElseThrow();
        
        if(request.getStatus() == RequestStatus.WAITING) {
            Optional<RequestActor> requestActor = requestActorRepository.findByRequestTypeIdAndIndex(studentRequests.getRequestTypeId(), studentRequests.getCurrentIndex() + 1);
            if(requestActor.isPresent()) {
                request.setCurrentIndex(request.getCurrentIndex() + 1);
                studentRequestRepository.save(request);
                return requestActor.get().getStaffId();
            } else {
                request.setStatus(RequestStatus.NEED_AFFIRMATION);
                studentRequestRepository.save(request);
                return studentRequests.getStudentId();
            }
        }else if(request.getStatus() == RequestStatus.NEED_AFFIRMATION){
            if(request.getCurrentIndex() == 0){
                request.setStatus(RequestStatus.ACCEPTED);
                studentRequestRepository.save(request);
                return studentRequests.getStudentId();
            }
            request.setCurrentIndex(request.getCurrentIndex() - 1);
            studentRequestRepository.save(request);
            return request.getStudentId();
        }else {
            throw new RuntimeException("Request is already accepted or rejected");
        }
    }

    // Sets the index to negative_current_index - 1
    // So we can find who rejected by calling Math.abs(current_index) + 1
    // If current_index = 0, then the request is rejected by the advisor
    // if current_index > 0, then search in request_actors table for current_index == index && request_type_id == request_type_id
    @Override
    public void rejectRequest(StudentRequests studentRequests) {
        StudentRequests request = studentRequestRepository.findByStudentIdAndRequestTypeIdAndWhen(
                studentRequests.getStudentId(),
                studentRequests.getRequestTypeId(),
                studentRequests.getWhen()
        ).orElseThrow();
        request.setCurrentIndex(-1 * request.getCurrentIndex() - 1);
        request.setStatus(RequestStatus.REJECTED);
        studentRequestRepository.save(request);
    }

    // Checks if the next actor is the one accepting the request
    // returns true if it is, false otherwise
    @Override
    public boolean checkIfNextActorIsTheOneAcceptingTheRequest(Long staffId, Long request_type_id, Integer current_index) {
        Optional<RequestActor> requestActor = requestActorRepository.findByRequestTypeIdAndIndex(request_type_id, current_index + 1);
        return requestActor.map(actor -> Objects.equals(actor.getStaffId(), staffId)).orElse(false);
    }

    //this methods checks if the request actor is the one who is accepting the request
    @Override
    public boolean checkIfRequestActorIsTrue(Long staffId, Long requestTypeId, int index) {
        Optional<RequestActor> requestActor = requestActorRepository.findByRequestTypeIdAndIndex(requestTypeId, index);
        if(requestActor.isPresent()) {
            return requestActor.get().getStaffId() == staffId;
        } else if(index==0){
            return true;
        } 
        else {
            return false;
        }
    }

    @Override
    public List<ListRequestTypes> getAllRequestTypes() {
        return requestTypeRepository.findAll().stream()
                .map(requestType -> new ListRequestTypes(requestType.getId(), requestType.getRequestName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ListRequestTypes> getAllRequestTypesByDepartmentId(Long departmentId) {
        return requestTypeRepository.findByDepartmentId(departmentId).stream()
                .map(requestType -> new ListRequestTypes(requestType.getId(), requestType.getRequestName()))
                .collect(Collectors.toList());
    }


}
