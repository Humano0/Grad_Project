package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.DTO.User;
import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.Presentation.ResponseClasses.LoginResponse;
import com.final_project.eduflow.Presentation.ResponseClasses.LogoutResponse;
import com.final_project.eduflow.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AuthController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginEntity entity, HttpServletResponse response) {
        try {
            User user = userService.findUser(entity);
            if(user != null) {
                String token = JwtUtil.createToken(user);
                Cookie cookie = new Cookie("token", token);
                cookie.setHttpOnly(false);
                response.addCookie(cookie);
                return ResponseEntity.ok(new LoginResponse("success", "Login successful", user.getRole()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("unauthorized","Invalid credentials", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("error", e.getMessage(), null));
        }
    }

    // for some unknown reason, the name "logout" endpoint is not working
    // so I changed it to "letmeout"
    @GetMapping("/letmeout")
    public ResponseEntity<LogoutResponse> logout(HttpServletResponse response) {
        System.out.println("Logout");
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(new LogoutResponse("success", "Logout successful"));
    }
}
