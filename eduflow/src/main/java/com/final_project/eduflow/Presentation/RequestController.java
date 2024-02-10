package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Data.DTO.ListRequestsEntity;
import com.final_project.eduflow.Data.DTO.RequestTypesEntity;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.Data.View.RequestRequirementView;
import com.final_project.eduflow.Data.View.StudentRequestsListingView;
import com.final_project.eduflow.DataAccess.*;
import com.final_project.eduflow.Presentation.ResponseClasses.AcceptRequestResponseMessage;
import com.final_project.eduflow.Services.RequestService;
import com.final_project.eduflow.Services.UserService;
import io.jsonwebtoken.Claims;
import com.final_project.eduflow.Config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.final_project.eduflow.Data.Entities.RequestType;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class RequestController {
    
    private final StudentRequestRepository studentRequestRepository;
    private final RequestRequirementViewRepository requestRequirementViewRepository;
    private final RequestService requestService;
    private final NotificationController notificationController;

    @Autowired
    public RequestController(StudentRequestRepository studentRequestRepository, RequestRequirementViewRepository requestRequirementViewRepository, RequestService requestService, NotificationController notificationController) {
        this.studentRequestRepository = studentRequestRepository;
        this.requestRequirementViewRepository = requestRequirementViewRepository;
        this.requestService = requestService;
        this.notificationController = notificationController;
    }

    // List student requests for student
    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/studentRequests")
    public ResponseEntity<List<StudentRequestsListingView>> getStudentRequest(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);
        return ResponseEntity.ok(requestService.getStudentRequestsById(id));
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/requestRequirements/{id}")
    public ResponseEntity<List<RequestRequirementView>> getRequestRequirements(@PathVariable("id") Long id) {
        List<RequestRequirementView> requestRequirementViews = requestRequirementViewRepository.findByRequestId(id);
        if (requestRequirementViews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(requestRequirementViews);
    }

    @PreAuthorize("hasAuthority('Student')")
    @PostMapping("/makeRequest")
    public ResponseEntity<?> makeRequest(@RequestBody StudentRequests newRequests, HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);
        
        studentRequestRepository.save(new StudentRequests(id, newRequests.getRequestTypeId(), newRequests.getInformation(), newRequests.getAddition()));

        return ResponseEntity.ok("Request is made successfully");
    }

    @PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty')")
    @GetMapping("/acceptRequest")
    public ResponseEntity<AcceptRequestResponseMessage> acceptRequest(@RequestBody StudentRequests studentRequest, HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long staffId = JwtUtil.getId(claims);
        if(requestService.checkIfRequestActorIsTrue(staffId, staffId, studentRequest.getCurrentIndex())){
            if(requestService.checkIfNextActorIsTheOneAcceptingTheRequest(staffId, studentRequest.getRequestTypeId(), studentRequest.getCurrentIndex())) {
                // frontend
                // TODO: some notification pops up to display the message if status == "back_to_back_same_actor"
                // TODO: If they accept, then call /acceptRequestForBackToBackSameActors
                return ResponseEntity.ok(new AcceptRequestResponseMessage("back_to_back_same_actor", "You are the next actor, do you want to accept the request?"));
            } else {
                Long subId = requestService.acceptRequest(studentRequest);
                if(subId == studentRequest.getStudentId()) {
                    notificationController.sendNotification(subId, "your request has been accepted");
                } else {
                    notificationController.sendNotification(subId, "new request waiting for your approval");
                }
                return ResponseEntity.ok(new AcceptRequestResponseMessage("accepted", "done and done"));
            }
        }else{
            return ResponseEntity.ok(new AcceptRequestResponseMessage("not_authorized", "You are not authorized to accept this request"));
        }
    }

    @PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty')")
    @GetMapping("/acceptRequestForBackToBackSameActors")
    public ResponseEntity<AcceptRequestResponseMessage> acceptRequestForBackToBackSameActors(@RequestBody StudentRequests studentRequest, HttpServletRequest request){
        Long subId = requestService.acceptRequest(studentRequest);
        if(subId == studentRequest.getStudentId()) {
            notificationController.sendNotification(subId, "your request has been accepted");
        } else {
            notificationController.sendNotification(subId, "new request waiting for your approval");
        }
        return ResponseEntity.ok(new AcceptRequestResponseMessage("accepted", "done and done"));
    }

    @PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty')")
    @GetMapping("/rejectRequest")
    public ResponseEntity<AcceptRequestResponseMessage> rejectRequest(@RequestBody StudentRequests studentRequest, @RequestBody String rejectionReason, HttpServletRequest request) {
        requestService.rejectRequest(studentRequest);
        notificationController.sendNotification(studentRequest.getStudentId(), "your request has been rejected");
        return ResponseEntity.ok(new AcceptRequestResponseMessage("accepted" ,"Request is rejected successfully"));
    }
}

//bildirilen kisi id
//olusturulma tarihi
//        bildirim okundu mu
