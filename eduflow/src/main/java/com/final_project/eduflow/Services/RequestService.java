package com.final_project.eduflow.Services;


import com.final_project.eduflow.Data.Entities.IdClasses.RequestStatus;
import com.final_project.eduflow.Data.Entities.RequestActor;
import com.final_project.eduflow.Data.Entities.RequestRejectionReason;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.View.StudentRequestsListingView;

import com.final_project.eduflow.DataAccess.*;
import com.final_project.eduflow.Presentation.RequestBodyParams.CancelRequestObject;
import com.final_project.eduflow.Presentation.ResponseClasses.ListRequestTypes;
import com.final_project.eduflow.Services.Interfaces.IRequestService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService implements IRequestService {
    private final SimpMessagingTemplate messagingTemplate;
    private final StudentRequestsListingViewRepository studentRequestsListingViewRepository;
    private final StudentRequestRepository studentRequestRepository;
    private final RequestActorRepository requestActorRepository;
    private final RequestTypeRepository requestTypeRepository;
    private final StudentRepository studentRepository;
    private final RequestRejectionReasonRepository requestrejectionreasonRepository;

    public RequestService(SimpMessagingTemplate messagingTemplate, StudentRequestsListingViewRepository studentRequestsListingViewRepository,
                          StudentRequestRepository studentRequestRepository,
                          RequestActorRepository requestActorRepository,
                          RequestTypeRepository requestTypeRepository,
                          StudentRepository studentRepository,
                          RequestRejectionReasonRepository requestrejectionreasonRepository
    ) {
        this.messagingTemplate = messagingTemplate;
        this.studentRequestsListingViewRepository = studentRequestsListingViewRepository;
        this.studentRequestRepository = studentRequestRepository;
        this.requestActorRepository = requestActorRepository;
        this.requestTypeRepository = requestTypeRepository;
        this.studentRepository = studentRepository;
        this.requestrejectionreasonRepository = requestrejectionreasonRepository;
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
                this.messagingTemplate.convertAndSendToUser(requestActor.get().getStaffId().toString(),
                        "/queue/notification",
                        "new request");
                return requestActor.get().getStaffId();
            } else {
                request.setStatus(RequestStatus.NEED_AFFIRMATION);
                request.setCurrentIndex(request.getCurrentIndex() - 1);
                Optional<RequestActor> reqActor = requestActorRepository.findByRequestTypeIdAndIndex(studentRequests.getRequestTypeId(), request.getCurrentIndex());
                if(request.getCurrentIndex() == 0) {
                    Long danismanId = studentRepository.findById(request.getStudentId()).orElseThrow().getAdvisorId();
                    this.messagingTemplate.convertAndSendToUser(danismanId.toString(),
                            "/queue/notification",
                            "need approval");
                            studentRequestRepository.save(request);
                    return danismanId;
                }
/*                 this.messagingTemplate.convertAndSendToUser(reqActor.get().getStaffId().toString(),
                        "/request/notification",
                        "needaffirmation"); */
                studentRequestRepository.save(request);
                return reqActor.get().getStaffId();
            }
        } else if(request.getStatus() == RequestStatus.NEED_AFFIRMATION){
            request.setStatus(RequestStatus.ACCEPTED);
            studentRequestRepository.save(request);
            this.messagingTemplate.convertAndSendToUser(request.getStudentId().toString(),
                    "/queue/notification",
                    "concluded");
            Long danismanId = studentRepository.findById(request.getStudentId()).orElseThrow().getAdvisorId();
            this.messagingTemplate.convertAndSendToUser(danismanId.toString(),
                    "/queue/notification",
                    "concluded");
            List <RequestActor> requestActors = requestActorRepository.findByRequestTypeId(request.getRequestTypeId());

            requestActors.stream().forEach( requestActor -> {
                this.messagingTemplate.convertAndSendToUser(requestActor.getStaffId().toString(),
                        "/queue/notification",
                        "concluded");
            });
            
            return request.getStudentId();
            
/*             if(request.getCurrentIndex() == 0){
                request.setStatus(RequestStatus.ACCEPTED);
                studentRequestRepository.save(request);
                this.messagingTemplate.convertAndSendToUser(request.getStudentId().toString(),
                        "/request/notification",
                        "accepted");
                return studentRequests.getStudentId();
            }
            request.setCurrentIndex(request.getCurrentIndex() - 1);
            if(request.getCurrentIndex() == 0) {
                Long danismanId = studentRepository.findById(request.getStudentId()).orElseThrow().getAdvisorId();
                this.messagingTemplate.convertAndSendToUser(danismanId.toString(),
                        "/request/notification",
                        "newrequest");
                studentRequestRepository.save(request);
                return danismanId;
            }
            studentRequestRepository.save(request);
            return requestActorRepository.findByRequestTypeIdAndIndex(studentRequests.getRequestTypeId(), request.getCurrentIndex()).orElseThrow().getStaffId(); */
        }else {
            throw new RuntimeException("Request is already accepted or rejected");
        }
    }

    // Sets the index to negative_current_index - 1
    // So we can find who rejected by calling Math.abs(current_index) + 1
    // If current_index = 0, then the request is rejected by the advisor
    // if current_index > 0, then search in request_actors table for current_index == index && request_type_id == request_type_id
    @Override
    public void rejectRequest(CancelRequestObject studentRequests) {
        StudentRequests request = studentRequestRepository.findByStudentIdAndRequestTypeIdAndWhen(
                studentRequests.getRequestStudentId(),
                studentRequests.getRequestTypeId(),
                studentRequests.getRequestWhenCreated()
        ).orElseThrow();
        //request.setCurrentIndex(-1 * request.getCurrentIndex() - 1);
        request.setStatus(RequestStatus.REJECTED);
        RequestRejectionReason requestRejectionReason = new RequestRejectionReason(request.getUniqueRequestId(), studentRequests.getCancellationReason(), request.getStatus().toString());
        this.messagingTemplate.convertAndSendToUser(request.getStudentId().toString(),
                "queue/notification",
                "concluded");
        requestrejectionreasonRepository.save(requestRejectionReason);
        studentRequestRepository.save(request);
    }

    @Override
    public void cancelRequest(StudentRequests studentRequests, String CancellationReason) {
        studentRequests.setStatus(RequestStatus.CANCELLED);
        RequestRejectionReason requestRejectionReason = new RequestRejectionReason(studentRequests.getUniqueRequestId(), CancellationReason, studentRequests.getStatus().toString());
        Long currentActorId = requestActorRepository.findByRequestTypeIdAndIndex(studentRequests.getRequestTypeId(), studentRequests.getCurrentIndex()).orElseThrow().getStaffId();
        this.messagingTemplate.convertAndSendToUser(currentActorId.toString(),
                "queue/notification",
                "concluded");
        this.messagingTemplate.convertAndSendToUser(studentRequests.getStudentId().toString(),
                "queue/notification",
                "concluded");
        requestrejectionreasonRepository.save(requestRejectionReason);
        studentRequestRepository.save(studentRequests);
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
