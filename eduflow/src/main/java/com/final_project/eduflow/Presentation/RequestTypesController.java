package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Data.DTO.RequestTypesEntity;
import com.final_project.eduflow.Data.Entities.RequestType;
import com.final_project.eduflow.DataAccess.RequestTypeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RequestTypesController {
    private final RequestTypeRepository requestTypeRepository;

    public RequestTypesController(RequestTypeRepository requestTypeRepository) {
        this.requestTypeRepository = requestTypeRepository;
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/requestTypes")
    public ResponseEntity<List<RequestTypesEntity>> getRequestTypes(HttpServletRequest request){
        List<RequestType> requestTypes = requestTypeRepository.findAll();
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
}
