package com.final_project.eduflow;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.datalayer.Dto.LoginUser;
import com.final_project.datalayer.Dto.User;
import com.final_project.services.Interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class LoginController {
    
    private final IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginUser entity) {
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
