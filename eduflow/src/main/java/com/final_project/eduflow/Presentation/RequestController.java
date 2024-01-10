package com.final_project.eduflow.Presentation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.DTO.NewRequestEntity;
import com.final_project.eduflow.Data.Entities.RequestType;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.View.RequestRequirementView;
import com.final_project.eduflow.DataAccess.RequestRequirementRepository;
import com.final_project.eduflow.DataAccess.RequestTypeRepository;
import com.final_project.eduflow.DataAccess.StudentRequestRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class RequestController {
    
    private final StudentRequestRepository studentRequestRepository;
    private final RequestTypeRepository requestTypeRepository;
    private final RequestRequirementRepository requestRequirementRepository;

    @Autowired
    public RequestController(StudentRequestRepository studentRequestRepository, RequestTypeRepository requestTypeRepository,
     RequestRequirementRepository requestRequirementRepository) {
        this.studentRequestRepository = studentRequestRepository;
        this.requestTypeRepository = requestTypeRepository;
        this.requestRequirementRepository = requestRequirementRepository;
    }

    @PreAuthorize("hasAuthority('Adviser')")
    @GetMapping("/RequestParams")
    public ResponseEntity<String> getRequestParams(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/AdviserRequest")
    public void getAdviserRequest(){

    }

    @GetMapping("/StudentRequests/{studentId}")
    public ResponseEntity<List<StudentRequests>> getStudentRequest( @PathVariable int studentId){
        List<StudentRequests> requests = studentRequestRepository.findByStudentId(studentId);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/makeRequest")
    public ResponseEntity<?> makeRequest(@RequestBody NewRequestEntity requestEntity){
        var response  = studentRequestRepository.save( new StudentRequests(requestEntity.getStudentId(), requestEntity.getRequestTypeId(),
        requestEntity.getInformation(),  "noAddition" ));
        return ResponseEntity.ok(response);
    }


/*
 *     public StudentRequests(long studentId, int requestTypeId, String information, String addition) {
        this.studentId = studentId;
        this.requestTypeId = requestTypeId;
        this.when = LocalDateTime.now();
        this.information = information;
        this.addition = addition;
        this.currentIndex = 0;
    }
       */

    @PutMapping("path/{id}")
    public void updateRequest(@PathVariable String id){

    }

    @GetMapping("/GetAllRequestTypes")
    public ResponseEntity<List<RequestType>> getAllRequestTypes(){
        List<RequestType> requestTypes = requestTypeRepository.findAll();
        return ResponseEntity.ok(requestTypes);
    }

    @GetMapping("/GetRequestParams/{requestTypeId}")
    public ResponseEntity<List<RequestRequirementView>> getMethodName(@PathVariable int requestTypeId) {
        List<RequestRequirementView> requestTypes = requestRequirementRepository.findByRequestId(requestTypeId);
        return ResponseEntity.ok(requestTypes);
    }

}
