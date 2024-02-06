package com.final_project.eduflow.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.Entities.RequestActor;
import com.final_project.eduflow.DataAccess.RequestActorRepository;

@RestController
public class RequestActorsController {
    
    private final RequestActorRepository requestActorsRepository;

    @Autowired
    public RequestActorsController(RequestActorRepository requestActorsRepository) {
        this.requestActorsRepository = requestActorsRepository;
    }

    @PostMapping("/AddNewRequestActor")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<RequestActor> addNewRequestActor(@RequestBody RequestActor requestActor){
        RequestActor newActor = requestActorsRepository.save(requestActor);
    
        return ResponseEntity.ok(newActor);
    }
}
