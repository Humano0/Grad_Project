package com.final_project.eduflow.Presentation;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.Entities.RequestActor;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.DataAccess.RequestActorRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;

import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class RequestActorsController {
    
    private final RequestActorRepository requestActorsRepository;
    private final TeachingStaffRepository teachinhgStaffRepository;

    public RequestActorsController(RequestActorRepository requestActorsRepository, TeachingStaffRepository teachinhgStaffRepository) {
        this.requestActorsRepository = requestActorsRepository;
        this.teachinhgStaffRepository = teachinhgStaffRepository;
    }

    @GetMapping("/GetRequestActors/{requestTypeId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<?>> getRequestActors(@PathVariable("requestTypeId") Long requestTypeId){
        List<RequestActor> requestActors = requestActorsRepository.findByRequestTypeId(requestTypeId);

        List<?> actors = requestActors.stream().map(requestActor->{
            TeachingStaff staff = teachinhgStaffRepository.findById(requestActor.getStaffId()).get();
            var actor = new Object(){
                public Long requestTypeId = requestActor.getRequestTypeId();
                public Long staffId = requestActor.getStaffId();
                public int index = requestActor.getIndex();
                public String staffName = staff.getName();
                public String staffRole = staff.getRole();
            };
            return actor;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(actors);
    }

    @Transactional
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/updateActors")
    public ResponseEntity<String> updateRequestActort( @RequestBody List<RequestActor> requestActors){
        requestActorsRepository.deleteByRequestTypeId(requestActors.get(0).getRequestTypeId());
        requestActors.forEach(requestActorsRepository::save);
        return ResponseEntity.ok("Request actors added successfully");
    }

    @PostMapping("/AddNewRequestActor")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<RequestActor> addNewRequestActor(@RequestBody RequestActor requestActor){
        RequestActor newActor = requestActorsRepository.save(requestActor);
        return ResponseEntity.ok(newActor);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addAllRequestActors")
    public void addAllRequestActors(@RequestBody List<RequestActor> requestActors){
        for(RequestActor requestActor : requestActors) {
            requestActorsRepository.save(requestActor);
        }
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
            if(index!=1){
                Optional <RequestActor>  requestActorPrevIndex= requestActorsRepository.findByRequestTypeIdAndIndex(entity.getStaffId(),(int) entity.getIndex()-1);
                if(!requestActorPrevIndex.isPresent()){
                    return ResponseEntity.badRequest().body("Previous index does not exist");
                }
            }
            requestActorsRepository.delete(requestActor.get());
            requestActorsRepository.save(entity);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
}
