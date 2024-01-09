package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.DTO.User;
import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginEntity entity, HttpServletResponse response) {
        try {
            User user = userService.findUser(entity);
            if(user != null) {
                String token = JwtUtil.createToken(user);
                Cookie cookie = new Cookie("token", token);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                return ResponseEntity.ok("Logged in successfully");
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + e.getMessage());
        }
    }
}
