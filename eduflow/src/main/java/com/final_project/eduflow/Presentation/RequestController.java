package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Data.DTO.ListRequestsEntity;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.DataAccess.*;
import io.jsonwebtoken.Claims;
import com.final_project.eduflow.Config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import com.final_project.eduflow.Data.DTO.NewRequestEntity;
import com.final_project.eduflow.Data.Entities.RequestType;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Data.View.RequestRequirementView;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
@RestController
public class RequestController {
    
    private final StudentRequestRepository studentRequestRepository;
    private final RequestTypeRepository requestTypeRepository;
    private final RequestRequirementRepository requestRequirementRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final StudentRepository studentRepository;
    private final TeachingStaffRepository teachingStaffRepository;


    @Autowired
    public RequestController(StudentRequestRepository studentRequestRepository, RequestTypeRepository requestTypeRepository,
     RequestRequirementRepository requestRequirementRepository,
                             StudentRepository studentRepository,
                             TeachingStaffRepository teachingStaffRepository) {
        this.studentRequestRepository = studentRequestRepository;
        this.requestTypeRepository = requestTypeRepository;
        this.requestRequirementRepository = requestRequirementRepository;
        this.studentRepository = studentRepository;
        this.teachingStaffRepository = teachingStaffRepository;
    }

    @PreAuthorize("hasAuthority('Adviser')")
    @GetMapping("/RequestParams")
    public ResponseEntity<String> getRequestParams(){
        return ResponseEntity.ok("hello");
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/StudentRequests")
    public ResponseEntity<List<ListRequestsEntity>> getStudentRequest(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // return 401 status
        }
        Long id = JwtUtil.getId(claims);
        List<StudentRequests> studentRequests = studentRequestRepository.findByStudentId(id);
        List<ListRequestsEntity> requests = studentRequests.stream().map(studentRequest -> {
            ListRequestsEntity entity = new ListRequestsEntity();
            entity.setTitle(studentRequest.getInformation());
            entity.setDate(studentRequest.getWhen().toLocalDate()); // Convert LocalDateTime to LocalDate
            entity.setStatus("Onaylandi");
            return entity;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(requests);
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/getAdvisor")
    public ResponseEntity<TeachingStaff> getAdvisor(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // return 401 status
        }
        Long id = JwtUtil.getId(claims);
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null) {
            Long advisorId = student.getAdvisorId();
            TeachingStaff advisor = teachingStaffRepository.findById(advisorId);

            if(advisor == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            else
                return ResponseEntity.ok(advisor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/returnToken")
    public ResponseEntity<String> getToken(HttpServletRequest request) {
        String token = JwtUtil.resolveToken(request);
        return ResponseEntity.ok(token);
    }

//    @GetMapping("/AdviserRequest")
//    public void getAdviserRequest(){
//
//    }
//    @PostMapping("/makeRequest")
//    public ResponseEntity<?> makeRequest(@RequestBody NewRequestEntity requestEntity){
//        var response  = studentRequestRepository.save( new StudentRequests(requestEntity.getStudentId(), requestEntity.getRequestTypeId(),
//        requestEntity.getInformation(),  "noAddition" ));
//        return ResponseEntity.ok(response);
//    }


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

//    @PutMapping("path/{id}")
//    public void updateRequest(@PathVariable String id){
//
//    }
//
//    @GetMapping("/GetAllRequestTypes")
//    public ResponseEntity<List<RequestType>> getAllRequestTypes(){
//        List<RequestType> requestTypes = requestTypeRepository.findAll();
//        return ResponseEntity.ok(requestTypes);
//    }
//
//    @GetMapping("/GetRequestParams/{requestTypeId}")
//    public ResponseEntity<List<RequestRequirementView>> getMethodName(@PathVariable int requestTypeId) {
//        List<RequestRequirementView> requestTypes = requestRequirementRepository.findByRequestId(requestTypeId);
//        return ResponseEntity.ok(requestTypes);
//    }

}
