package com.final_project.eduflow.Presentation;


import com.final_project.eduflow.Data.Entities.RequestRequirement;
import com.final_project.eduflow.Data.View.RequestRequirementView;
import com.final_project.eduflow.Data.View.StudentRequestsListingView;

import com.final_project.eduflow.DataAccess.*;
import com.final_project.eduflow.Presentation.ResponseClasses.AcceptRequestResponseMessage;
import com.final_project.eduflow.Services.NotificationService;
import com.final_project.eduflow.Services.RequestService;

import io.jsonwebtoken.Claims;
import com.final_project.eduflow.Config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.final_project.eduflow.Data.Entities.StudentRequests;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class RequestController {
    
    private final StudentRequestRepository studentRequestRepository;
    private final RequestRequirementRepository requestRequirementRepository;
    private final RequestService requestService;
    private final NotificationService notificationService;


    @Autowired
    public RequestController(StudentRequestRepository studentRequestRepository, RequestRequirementRepository requestRequirementRepository, RequestService requestService, NotificationService notificationService) {
        this.studentRequestRepository = studentRequestRepository;
        this.requestRequirementRepository = requestRequirementRepository;
        this.requestService = requestService;
        this.notificationService = notificationService;
    }

    // List student requests for student
    // needs
    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/studentRequests")
    public ResponseEntity<List<StudentRequests>> getStudentRequest(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        System.out.println(claims);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);
        return ResponseEntity.ok(studentRequestRepository.findByStudentId(id));
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/requestRequirements/{id}")
    public ResponseEntity<List<RequestRequirement>> getRequestRequirements(@PathVariable("id") Long id) {
        List<RequestRequirement> requestRequirements = requestRequirementRepository.findByRequestTypeId(id);
        if (requestRequirements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(requestRequirements);
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

    //@PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty')")
    @PreAuthorize("hasAnyAuthority('Danisman', 'Bolum', 'Dekanlik','Advisor', 'Head_of_Department', 'Dean_of_Faculty')")
    @PostMapping("/acceptRequest")
    public ResponseEntity<AcceptRequestResponseMessage> acceptRequest(@RequestBody StudentRequests studentRequest, HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long staffId = JwtUtil.getId(claims);
        if(requestService.checkIfRequestActorIsTrue(staffId, studentRequest.getRequestTypeId(), studentRequest.getCurrentIndex())){
            if(requestService.checkIfNextActorIsTheOneAcceptingTheRequest(staffId, studentRequest.getRequestTypeId(), studentRequest.getCurrentIndex())) {
                return ResponseEntity.ok(new AcceptRequestResponseMessage("back_to_back_same_actor", "You are the next actor, do you want to accept the request?"));
            } else {
                Long subId = requestService.acceptRequest(studentRequest);
                if(subId == studentRequest.getStudentId()) {
                    notificationService.sendNotification(subId, "your request has been accepted");
                } else {
                    notificationService.sendNotification(subId, "new request waiting for your approval");
                }
                return ResponseEntity.ok(new AcceptRequestResponseMessage("accepted", "done and done"));
            }
        }else{
            return ResponseEntity.ok(new AcceptRequestResponseMessage("not_authorized", "You are not authorized to accept this request"));
        }
    }

    //@PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty')")
    @PreAuthorize("hasAnyAuthority('Danisman', 'Bolum', 'Dekanlik')")
    @PostMapping("/acceptRequestForBackToBackSameActors")
    public ResponseEntity<AcceptRequestResponseMessage> acceptRequestForBackToBackSameActors(@RequestBody StudentRequests studentRequest, HttpServletRequest request){
        Long subId = requestService.acceptRequest(studentRequest);
        if(subId == studentRequest.getStudentId()) {
            notificationService.sendNotification(subId, "your request has been accepted");
        } else {
            notificationService.sendNotification(subId, "new request waiting for your approval");
        }
        return ResponseEntity.ok(new AcceptRequestResponseMessage("accepted", "done and done"));
    }

    //@PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty')")
    @PreAuthorize("hasAnyAuthority('Danisman', 'Bolum', 'Dekanlik','Advisor','Head_of_Department', 'Dean_of_Faculty')")
    @PostMapping("/rejectRequest")
    public ResponseEntity<AcceptRequestResponseMessage> rejectRequest(@RequestBody StudentRequests studentRequest, HttpServletRequest request) {
        requestService.rejectRequest(studentRequest);
        notificationService.sendNotification(studentRequest.getStudentId(), "your request has been rejected");
        return ResponseEntity.ok(new AcceptRequestResponseMessage("accepted" ,"Request is rejected successfully"));
    }
}