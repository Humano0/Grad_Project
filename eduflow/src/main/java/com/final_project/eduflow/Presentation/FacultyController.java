package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Services.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllFaculties")
    public ResponseEntity<?> getAllFaculties(){
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

}
