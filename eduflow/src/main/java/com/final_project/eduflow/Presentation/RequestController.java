package com.final_project.eduflow.Presentation;

import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.Entities.RequestType;
import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.DataAccess.RequestTypeRepository;
import com.final_project.eduflow.DataAccess.StudentRequestRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class RequestController {
    
    private final StudentRequestRepository studentRequestRepository;
    private final RequestTypeRepository requestTypeRepository;

    @Autowired
    public RequestController(StudentRequestRepository studentRequestRepository, RequestTypeRepository requestTypeRepository) {
        this.studentRequestRepository = studentRequestRepository;
        this.requestTypeRepository = requestTypeRepository;
    }

    @GetMapping("/RequestParams")
    public void getRequestParams(){

    }

    @GetMapping("/AdviserRequest")
    public void getAdviserRequest(){

    }

    @GetMapping("/StudentRequest")
    public void getStudentRequest(){

    }

    @PostMapping("/makeRequest")
    public void makeRequest(@RequestBody StudentRequests studentRequest){
        studentRequestRepository.save( studentRequest);
    }
    
    @PutMapping("path/{id}")
    public void updateRequest(@PathVariable String id){

    }

    @GetMapping("/GetAllRequestTypes")
    public ResponseEntity<List<RequestType>> getAllRequestTypes(){
        List<RequestType> requestTypes = requestTypeRepository.findAll();
        return ResponseEntity.ok(requestTypes);
    }
}
