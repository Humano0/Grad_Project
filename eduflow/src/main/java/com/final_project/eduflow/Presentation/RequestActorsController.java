package com.final_project.eduflow.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.Entities.RequestActor;
import com.final_project.eduflow.DataAccess.RequestActorRepository;


import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class RequestActorsController {
    
    private final RequestActorRepository requestActorsRepository;

    @Autowired
    public RequestActorsController(RequestActorRepository requestActorsRepository) {
        this.requestActorsRepository = requestActorsRepository;
    }

    @GetMapping("/GetRequestActors/{requestTypeId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<RequestActor>> getRequestActors(@PathVariable("requestTypeId") Long requestTypeId){
        List<RequestActor> requestActors = requestActorsRepository.findByRequestTypeId(requestTypeId);

        return ResponseEntity.ok(requestActors);
    }

    @PostMapping("/AddNewRequestActor")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<RequestActor> addNewRequestActor(@RequestBody RequestActor requestActor){
        RequestActor newActor = requestActorsRepository.save(requestActor);
    
        return ResponseEntity.ok(newActor);
    }


    @SuppressWarnings("null")
    @DeleteMapping("/DeleteRequestActor/{requestTypeId}/{staffId}/{index}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<RequestActor> deleteRequestActor(@PathVariable("requestTypeId") Long requestTypeId, @PathVariable("staffId") Long staffId,@PathVariable("index") int index){

        Optional<RequestActor> requestActor = requestActorsRepository.findByRequestTypeIdAndStaffIdAndIndex(requestTypeId, staffId, index);

        if(requestActor.isPresent()){
            requestActorsRepository.delete(requestActor.get());
            return ResponseEntity.ok(requestActor.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("updateRequestActor/{requestTypeId}/{staffId} /{index}")
    public ResponseEntity<?> updateRequestActor(@PathVariable long id,@PathVariable long staffId, @PathVariable int index, @RequestBody RequestActor entity) {

        Optional <RequestActor> requestActor = requestActorsRepository.findByRequestTypeIdAndStaffIdAndIndex(id,staffId,index);
        if(!requestActor.isPresent()){
            return ResponseEntity.notFound().build();
        }
        try{
            Optional <RequestActor> requestActorWithSameIndex = requestActorsRepository.findByRequestTypeIdAndIndex(entity.getStaffId(),(int) entity.getIndex());
            if(requestActorWithSameIndex.isPresent() && requestActorWithSameIndex.get().getStaffId() != index){
                return ResponseEntity.badRequest().build();
            }
            requestActorsRepository.delete(requestActor.get());
            requestActorsRepository.save(entity);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
}
