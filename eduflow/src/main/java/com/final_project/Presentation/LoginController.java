package com.final_project.Presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LoginController {
    
/*     @GetMapping("/login")
    public String login(){
        return "login";
    } */
    @GetMapping("/Debug")
    public String DebugMethod() {
        return "Debug";
    }
    
}
