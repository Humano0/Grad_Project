package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Data.DTO.ListRequestsEntity;
import com.final_project.eduflow.Data.DTO.RequestTypesEntity;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.Data.View.RequestRequirementView;
import com.final_project.eduflow.DataAccess.*;
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
    private final RequestRequirementRepository requestRequirementRepository;

    @Autowired
    public RequestController(StudentRequestRepository studentRequestRepository , RequestRequirementRepository requestRequirementRepository) {
        this.studentRequestRepository = studentRequestRepository;
        this.requestRequirementRepository = requestRequirementRepository;
    }

    // List student requests for student
    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/StudentRequests")
    public ResponseEntity<List<ListRequestsEntity>> getStudentRequest(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);
        List<StudentRequests> studentRequests = studentRequestRepository.findByStudentId(id);
        if(studentRequests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<ListRequestsEntity> requests = studentRequests.stream().map(studentRequest -> {
            ListRequestsEntity entity = new ListRequestsEntity();
            entity.setTitle(studentRequest.getInformation());
            entity.setDate(studentRequest.getWhen().toLocalDate());
            entity.setStatus("Onaylandi");
            return entity;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(requests);
    }

    // List student requests for advisor
    @PreAuthorize("hasAuthority('Advisor')")
    @GetMapping("/advisorRequests")
    public ResponseEntity<List<RequestTypesEntity>> getAdvisorRequests(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);

        // TODO: view all requests of students that are assigned to this advisor

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
//    @PostMapping("/makeRequest")
//    public ResponseEntity<?> makeRequest(@RequestBody NewRequestEntity requestEntity){
//        var response  = studentRequestRepository.save( new StudentRequests(requestEntity.getStudentId(), requestEntity.getRequestTypeId(),
//        requestEntity.getInformation(),  "noAddition" ));
//        return ResponseEntity.ok(response);
//    }

}
