package com.final_project.eduflow.Presentation;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.DataAccess.RequestRequirementRepository;

import jakarta.transaction.Transactional;


import com.final_project.eduflow.Data.Entities.RequestRequirement;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class RequestRequirementController {
    
    private final RequestRequirementRepository requestRequirementRepository;

    @Autowired
    public RequestRequirementController(RequestRequirementRepository requestRequirementRepository) {
        this.requestRequirementRepository = requestRequirementRepository;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/updateRequestRequirements")
    public ResponseEntity<String> addNewRequestRequirement( @RequestBody List<RequestRequirement> requestRequirement) {
        var requestTypeId = requestRequirement.get(0).getRequestTypeId();
        requestRequirementRepository.deleteByRequestTypeId(requestTypeId);
        requestRequirement.forEach(requestRequirementRepository::save);
        return ResponseEntity.ok("Request Requirements added successfully");
    }

/*     @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/updateRequirements")
    public ResponseEntity<RequestRequirement> updateRequestRequirement(@RequestBody RequestRequirement requestRequirement){
        RequestRequirement newRequestRequirement = requestRequirementRepository.save(requestRequirement);
        return ResponseEntity.ok(newRequestRequirement);
    } */

    @Transactional
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/updateRequirements")
    public ResponseEntity<RequestRequirement> updateRequestRequirement(@RequestBody List<RequestRequirement> requestRequirement){
        requestRequirementRepository.deleteByRequestTypeId(requestRequirement.get(0).getRequestTypeId());
        requestRequirement.forEach(requestRequirementRepository::save);

        return ResponseEntity.ok(requestRequirement.get(0));
    }


    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addAllRequirements")
    public void addAllRequirements(@RequestBody List<RequestRequirement> requestRequirements){
        for(RequestRequirement requestReq : requestRequirements) {
            requestRequirementRepository.save(requestReq);
        }
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

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("updateRequestRequirement/{requestTypeId}/{index}")
    public ResponseEntity<?> updateRequestRequirment(@PathVariable long requestTypeId, @PathVariable int index, @RequestBody RequestRequirement entity) {
        //TODO: process PUT request
        
        Optional <RequestRequirement> requestRequirement = requestRequirementRepository.findByRequestTypeIdAndIndex(requestTypeId,index);
        if(!requestRequirement.isPresent()){
            return ResponseEntity.notFound().build();
        }
        try{
            Optional <RequestRequirement> requestRequirementWithSameIndex = requestRequirementRepository.findByRequestTypeIdAndIndex(requestTypeId,entity.getIndex());
            if(requestRequirementWithSameIndex.isPresent() && requestRequirementWithSameIndex.get().getIndex() != index){
                return ResponseEntity.badRequest().body("Index already exists");
            }
            if(index!=1){
                Optional <RequestRequirement>  requestRequirementPrevIndex= requestRequirementRepository.findByRequestTypeIdAndIndex(requestTypeId,(int) entity.getIndex()-1);
                if(!requestRequirementPrevIndex.isPresent()){
                    return ResponseEntity.badRequest().body("Previous index does not exist");
                }
            }
            requestRequirementRepository.delete(requestRequirement.get());
            RequestRequirement result =requestRequirementRepository.save(entity);

            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
