package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllDepartments")
    public ResponseEntity<?> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllDepartmentsByFacultyId/{facultyId}")
    public ResponseEntity<?> getAllDepartmentsByFacultyId(@PathVariable Long facultyId){
        return ResponseEntity.ok(departmentService.getAllDepartmentsByFacultyId(facultyId));
    }
}
