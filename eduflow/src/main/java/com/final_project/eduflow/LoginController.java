package com.final_project.eduflow;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.datalayer.Dto.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class LoginController {
    
    @PostMapping("/login")
    public LoginUser Login(@RequestBody LoginUser entity) {
        
        return entity;
    }
    @GetMapping("/debug")
    public String getMethodName() {
        return "debug";
    }
    
}
