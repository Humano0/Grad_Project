package com.final_project.eduflow.Services;

import com.final_project.eduflow.Data.DTO.ListRequestsEntity;
import com.final_project.eduflow.Data.Entities.RequestActor;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.View.StudentRequestsListingView;
import com.final_project.eduflow.DataAccess.RequestActorRepository;
import com.final_project.eduflow.DataAccess.RequestTypeRepository;
import com.final_project.eduflow.DataAccess.StudentRequestRepository;
import com.final_project.eduflow.DataAccess.StudentRequestsListingViewRepository;
import com.final_project.eduflow.Services.Interfaces.IRequestService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RequestService implements IRequestService {

    private final StudentRequestsListingViewRepository studentRequestsListingViewRepository;
    private final StudentRequestRepository studentRequestRepository;
    private final RequestActorRepository requestActorRepository;

    public RequestService(StudentRequestsListingViewRepository studentRequestsListingViewRepository,
                          StudentRequestRepository studentRequestRepository,
                          RequestActorRepository requestActorRepository) {
        this.studentRequestsListingViewRepository = studentRequestsListingViewRepository;
        this.studentRequestRepository = studentRequestRepository;
        this.requestActorRepository = requestActorRepository;
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
        request.setCurrentIndex(request.getCurrentIndex() + 1);
        studentRequestRepository.save(request);
        Optional<RequestActor> requestActor = requestActorRepository.findByRequestTypeIdAndIndex(studentRequests.getRequestTypeId(), studentRequests.getCurrentIndex() + 1);
        if(requestActor.isPresent()) {
            return requestActor.get().getStaffId();
        } else {
            return studentRequests.getStudentId();
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
        studentRequestRepository.save(request);
    }

    // Checks if the next actor is the one accepting the request
    // returns true if it is, false otherwise
    @Override
    public boolean checkIfNextActorIsTheOneAcceptingTheRequest(Long staffId, Long request_type_id, Integer current_index) {
        Optional<RequestActor> requestActor = requestActorRepository.findByRequestTypeIdAndIndex(request_type_id, current_index + 1);
        return requestActor.map(actor -> Objects.equals(actor.getStaffId(), staffId)).orElse(false);
    }

    @Override
    public boolean checkIfRequestActorIsTrue(Long staffId, Long requestTypeId, int index) {
        Optional<RequestActor> requestActor = requestActorRepository.findByRequestTypeIdAndIndex(requestTypeId, index);

        if(requestActor.isPresent()) {
            return requestActor.get().getStaffId() == staffId;
        } else {
            return false;
        }

    }
}
