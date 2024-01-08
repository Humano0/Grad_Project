package com.final_project.eduflow.Presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.Data.DTO.User;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Services.Interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class LoginController {
    
    private final IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UserLoginEntity entity) {
        try {
            User user = userService.findUser(entity); // Assuming userService has a login method
            return ResponseEntity.ok(user); // Return the User object with a 200 OK status
        } catch (Exception e) {
            return ResponseEntity.badRequest().body( e.getMessage()); // Return the error message with a 400 Bad Request status
        }

    }
    @GetMapping("/debug")
    public String getMethodName() {
        return "debug";
    }
    
}
