package com.final_project.eduflow.Presentation;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class RequestController {
    
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
    public void makeRequest(){

    }
    
    @PutMapping("path/{id}")
    public void updateRequest(@PathVariable String id){

    }

}
