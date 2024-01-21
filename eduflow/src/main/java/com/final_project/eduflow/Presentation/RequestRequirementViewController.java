package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Data.DTO.ListRequestsEntity;
import com.final_project.eduflow.Data.View.RequestRequirementView;
import com.final_project.eduflow.DataAccess.RequestRequirementRepository;
import com.final_project.eduflow.DataAccess.RequestRequirementViewRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestRequirementViewController {
    private final RequestRequirementViewRepository requestRequirementViewRepository;

    public RequestRequirementViewController(RequestRequirementViewRepository requestRequirementViewRepository) {
        this.requestRequirementViewRepository = requestRequirementViewRepository;
    }

    @PreAuthorize("hasAuthority('Student')")
    @PostMapping("/requestRequirements/{id}")
    public ResponseEntity<List<RequestRequirementView>> getRequestRequirements(@PathVariable("id") Long id) {
        List<RequestRequirementView> requestRequirementViews = requestRequirementViewRepository.findByRequestId(id);
        if (requestRequirementViews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(requestRequirementViews);
    }
}
