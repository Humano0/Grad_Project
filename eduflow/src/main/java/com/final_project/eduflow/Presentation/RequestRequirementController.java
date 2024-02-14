package com.final_project.eduflow.Presentation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.DataAccess.RequestRequirementRepository;
import com.final_project.eduflow.Data.Entities.RequestRequirement;

@RestController
public class RequestRequirementController {
    
    private final RequestRequirementRepository requestRequirementRepository;

    @Autowired
    public RequestRequirementController(RequestRequirementRepository requestRequirementRepository) {
        this.requestRequirementRepository = requestRequirementRepository;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addNewRequirement")
    public ResponseEntity<RequestRequirement> addNewRequestRequirement(@RequestBody RequestRequirement requestRequirement){
        RequestRequirement newRequestRequirement = requestRequirementRepository.save(requestRequirement);
        return ResponseEntity.ok(newRequestRequirement);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping ("/getRequestRequirements/{requestTypeId}")
    public ResponseEntity<Iterable<RequestRequirement>> getRequestRequirements(@PathVariable long requestTypeId){
        Iterable<RequestRequirement> requestRequirements = requestRequirementRepository.findByRequestTypeId(requestTypeId);
        return ResponseEntity.ok(requestRequirements);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteRequirement/{requestTypeId}/{index}")
    public ResponseEntity<String> deleteRequestRequirement(@PathVariable long requestTypeId, @PathVariable int index){

        Optional <RequestRequirement> requestRequirement = requestRequirementRepository.findByRequestTypeIdAndIndex(requestTypeId,index);

        if(requestRequirement.isPresent()){
            requestRequirementRepository.delete(requestRequirement.get());
            return ResponseEntity.ok("Requirement "+requestRequirement.get()+ " deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
