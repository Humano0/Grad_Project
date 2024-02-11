package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.DTO.RequestTypesEntity;
import com.final_project.eduflow.Data.Entities.RequestType;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.DataAccess.RequestActorRepository;
import com.final_project.eduflow.DataAccess.RequestRequirementRepository;
import com.final_project.eduflow.DataAccess.RequestTypeRepository;
import com.final_project.eduflow.DataAccess.StudentRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Transactional
public class RequestTypesController {
    private final RequestTypeRepository requestTypeRepository;
    private final RequestActorRepository requestActorRepository;
    private final RequestRequirementRepository  requestRequirementRepository;
    private final StudentRepository studentRepository;

    public RequestTypesController(RequestTypeRepository requestTypeRepository, RequestActorRepository requestActorRepository, RequestRequirementRepository requestRequirementRepository, StudentRepository studentRepository) {
        this.requestTypeRepository = requestTypeRepository;
        this.requestActorRepository = requestActorRepository;
        this.requestRequirementRepository = requestRequirementRepository;
        this.studentRepository = studentRepository;
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/requestTypes")
    public ResponseEntity<List<RequestTypesEntity>> getRequestTypes(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(!studentOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        long departmentId = studentOptional.get().getDepartmentId();

        List<RequestType> requestTypes = requestTypeRepository.findByDepartmentId(departmentId);
        if(!requestTypes.isEmpty()) {
            List<RequestTypesEntity> requestTypesEntities = requestTypes.stream().map(requestType -> {
                RequestTypesEntity requestTypesEntity = new RequestTypesEntity();
                requestTypesEntity.setRequestName(requestType.getRequestName());
                requestTypesEntity.setId(requestType.getId());
                return requestTypesEntity;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(requestTypesEntities);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/requestTypes")
    public ResponseEntity<RequestType> addNewRequestType(@RequestBody RequestType requestType){
        RequestType newRequestType = requestTypeRepository.save(requestType);
        return ResponseEntity.ok(newRequestType);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/requestTypes/{id}")
    public ResponseEntity<RequestType> deleteRequestType(@PathVariable Long id){
/*         Optional<RequestType> requestType = requestTypeRepository.findById(id);
        if(requestType.isPresent()){
            requestTypeRepository.delete(requestType.get());
            return ResponseEntity.ok().build();
        } */
        Optional<RequestType> requestType = requestTypeRepository.findById(id);
        if(requestType.isPresent()){
            requestActorRepository.deleteByRequestTypeId(id);
            requestRequirementRepository.deleteByRequestTypeId(id);
            requestTypeRepository.delete(requestType.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

/*     @PreAuthorize("hasAuthority('Admnin')")
    @PostMapping("/newRequestType")
    public ResponseEntity<RequestType> addNewRequestType(@RequestBody RequestType requestType){
        RequestType newRequestType = requestTypeRepository.save(requestType);
        return ResponseEntity.ok(newRequestType);
    } */
}
