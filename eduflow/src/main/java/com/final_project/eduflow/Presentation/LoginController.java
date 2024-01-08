package com.final_project.eduflow.Presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class LoginController {
    
    private final TeachingStaffRepository staffRepository;

    @Autowired
    public LoginController(TeachingStaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UserLoginEntity entity) {
        return ResponseEntity.ok(staffRepository.findByEmail(entity.getEmail()));
    }
    @GetMapping("/debug")
    public String getMethodName() {
        return "debug";
    }
    
}
