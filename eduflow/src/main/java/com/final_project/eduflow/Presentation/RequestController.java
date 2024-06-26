package com.final_project.eduflow.Presentation;


import com.final_project.eduflow.Data.Entities.RequestRejectionReason;
import com.final_project.eduflow.Data.Entities.RequestRequirement;
import com.final_project.eduflow.Data.Entities.Student;

import com.final_project.eduflow.DataAccess.*;
import com.final_project.eduflow.Presentation.RequestBodyParams.CancelRequestObject;
import com.final_project.eduflow.Presentation.ResponseClasses.AcceptRequestResponseMessage;
import com.final_project.eduflow.Presentation.ResponseClasses.GetCommentId;
import com.final_project.eduflow.Presentation.ResponseClasses.ListReqsForStudent;
import com.final_project.eduflow.Services.CreateRequestPdf;
import com.final_project.eduflow.Services.NotificationService;
import com.final_project.eduflow.Services.RequestService;

import io.jsonwebtoken.Claims;
import com.final_project.eduflow.Config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.Entities.IdClasses.RequestStatus;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RestController
public class RequestController {
    
    private final StudentRequestRepository studentRequestRepository;
    private final RequestRequirementRepository requestRequirementRepository;
    private final RequestService requestService;
    private final NotificationService notificationService;
    private final CreateRequestPdf createRequestPdf;
    private final SimpMessagingTemplate messagingTemplate;
    private final StudentRepository studentRepository;
    private final RequestRejectionReasonRepository requestRejectionReasonRepository;


    @Autowired
    public RequestController(StudentRequestRepository studentRequestRepository, RequestRequirementRepository requestRequirementRepository,
                             RequestService requestService, NotificationService notificationService, CreateRequestPdf createRequestPdf, SimpMessagingTemplate messagingTemplate, StudentRepository studentRepository, RequestRejectionReasonRepository requestRejectionReasonRepository) {
        this.studentRequestRepository = studentRequestRepository;
        this.requestRequirementRepository = requestRequirementRepository;
        this.requestService = requestService;
        this.notificationService = notificationService;
        this.createRequestPdf = createRequestPdf;
        this.messagingTemplate = messagingTemplate;
        this.studentRepository = studentRepository;
        this.requestRejectionReasonRepository = requestRejectionReasonRepository;
    }

    // List student requests for student
    // needs
    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/studentRequests")
    public ResponseEntity<List<ListReqsForStudent>> getStudentRequest(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        System.out.println(claims);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);
        List<ListReqsForStudent> listReqsForStudents = new ArrayList<>();
        List<StudentRequests> sreq = studentRequestRepository.findByStudentId(id);
        for (StudentRequests studentRequest : sreq) {
            UUID requestUUID = studentRequest.getUniqueRequestId();
            RequestRejectionReason res = requestRejectionReasonRepository.findByRequestUUID(requestUUID);

            ListReqsForStudent listReqsForStudent = getListReqsForStudent(studentRequest, res);

            listReqsForStudents.add(listReqsForStudent);
        }
        return (ResponseEntity<List<ListReqsForStudent>>) listReqsForStudents;
    }

    private static ListReqsForStudent getListReqsForStudent(StudentRequests studentRequest, RequestRejectionReason res) {
        ListReqsForStudent listReqsForStudent = new ListReqsForStudent();
        listReqsForStudent.setStudentId(studentRequest.getStudentId());
        listReqsForStudent.setRequestTypeId(studentRequest.getRequestTypeId());
        listReqsForStudent.setWhen(studentRequest.getWhen());
        listReqsForStudent.setInformation(studentRequest.getInformation());
        listReqsForStudent.setAddition(studentRequest.getAddition());
        listReqsForStudent.setCurrentIndex(studentRequest.getCurrentIndex());
        listReqsForStudent.setStatus(studentRequest.getStatus());
        listReqsForStudent.setUniqueRequestId(studentRequest.getUniqueRequestId());

        if (res != null) {
            listReqsForStudent.setReason(res.getReason());
        } else {
            listReqsForStudent.setReason(null);
        }
        return listReqsForStudent;
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
        Student student = studentRepository.findById(id).orElseThrow();
        
        studentRequestRepository.save(new StudentRequests(id, newRequests.getRequestTypeId(), newRequests.getInformation(), newRequests.getAddition()));
        messagingTemplate.convertAndSendToUser(student.getAdvisorId().toString(), "queue/notification" , "new request");
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
        if(requestService.checkIfRequestActorIsTrue(staffId, studentRequest.getRequestTypeId(), studentRequest.getCurrentIndex())
            | studentRequest.getStatus() == RequestStatus.NEED_AFFIRMATION){
            System.out.println("here");
            Long subId = requestService.acceptRequest(studentRequest);
//                if(subId == studentRequest.getStudentId()) {
//                    notificationService.sendNotification(subId, "your request has been accepted");
//                } else {
//                    notificationService.sendNotification(subId, "new request waiting for your approval");
//                }
            return ResponseEntity.ok(new AcceptRequestResponseMessage("accepted", "done and done"));
            
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
    public ResponseEntity<AcceptRequestResponseMessage> rejectRequest(@RequestBody CancelRequestObject studentRequest) {
        requestService.rejectRequest(studentRequest);
        return ResponseEntity.ok(new AcceptRequestResponseMessage("accepted" ,"Request is rejected successfully"));
    }

    @PreAuthorize("hasAnyAuthority('Danisman', 'Bolum', 'Dekanlik')")
    @GetMapping("/getRequestPdf/{studentId}/{requestTypeId}/{when}")
    public ResponseEntity<?> getRequestPdf(@PathVariable("studentId") Long studentId, @PathVariable("requestId") Long requestId, @PathVariable("requestTypeId") OffsetDateTime when) {

        System.out.println("studentId: " + studentId + " requestId: " + requestId + " when: " + when);
        //StudentRequest request = studentRequestRepository.findByStudentIdAndRequestTypeIdAndWhe(studentId,requestId,).get();

        return ResponseEntity.ok("Request is made successfully");
    }

    // Cancels a request
    // needs
    // @BODY: {requestWhenCreated, requestStudentId, requestTypeId}
    // @RETURN: String
    //    Example request:
    //    {
    //        "requestWhenCreated": "2024-03-10T19:48:20.693+0300",
    //        "requestStudentId": "21896680",
    //        "requestTypeId": "1"
    //    }
    @PreAuthorize("hasAnyAuthority('Danisman', 'Bolum', 'Dekanlik','Advisor','Head_of_Department', 'Dean_of_Faculty', 'Student')")
    @PostMapping("/cancelRequest")
    public ResponseEntity<?> cancelRequest(@RequestBody CancelRequestObject requestInfo) {
        StudentRequests cancelledRequest = studentRequestRepository.findByStudentIdAndRequestTypeIdAndWhen(requestInfo.getRequestStudentId(), requestInfo.getRequestTypeId(), requestInfo.getRequestWhenCreated()).orElseThrow();
        requestService.cancelRequest(cancelledRequest, requestInfo.getCancellationReason());
        return ResponseEntity.ok("Request is cancelled successfully");
    }

    @PreAuthorize("hasAnyAuthority('Danisman', 'Bolum', 'Dekanlik','Advisor','Head_of_Department', 'Dean_of_Faculty')")
    @PostMapping("/getRequest")
    public ResponseEntity<?> getRequest(@RequestBody GetCommentId getCommentId) {
        return ResponseEntity.ok(studentRequestRepository.findByStudentIdAndRequestTypeIdAndWhen(getCommentId.getRequestStudentId(), getCommentId.getRequestTypeId(), getCommentId.getRequestWhenCreated()));
    }
}