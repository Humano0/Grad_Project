package com.final_project.eduflow;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.DataAccess.Repositories.StaffRepository;
import com.final_project.datalayer.Dto.LoginUser;
import com.final_project.datalayer.Dto.User;
import com.final_project.services.Interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class LoginController {
    
    private final StaffRepository staffRepository;

    @Autowired
    public LoginController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginUser entity) {
        return ResponseEntity.ok(staffRepository.findByEmail(entity.getEmail()));
    }
    @GetMapping("/debug")
    public String getMethodName() {
        return "debug";
    }
    
}
