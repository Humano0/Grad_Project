package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.DTO.User;
import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.Data.Entities.EntryLogs;
import com.final_project.eduflow.DataAccess.EntryLogsRepository;
import com.final_project.eduflow.Presentation.ResponseClasses.LoginResponse;
import com.final_project.eduflow.Presentation.ResponseClasses.LogoutResponse;
import com.final_project.eduflow.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AuthController {

    private final UserService userService;
    private final EntryLogsRepository entryLogsRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService, EntryLogsRepository entryLogsRepository) {
        this.userService = userService;
        this.entryLogsRepository = entryLogsRepository;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginEntity entity, HttpServletRequest request,HttpServletResponse response) {
        try {
            User user = userService.findUser(entity);
            if(user != null) {
                String token = JwtUtil.createToken(user);
                Cookie cookie = new Cookie("token", token);
                cookie.setHttpOnly(false);
                response.addCookie(cookie);

                // Log the user's ip address
                //System.out.println("Users ip address: " + request.getRemoteAddr());
                entryLogsRepository.save(new EntryLogs( request.getRemoteAddr(), user.getId()));

                return ResponseEntity.ok(new LoginResponse("success", "Login successful", user.getRole()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("unauthorized","Invalid credentials", null));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("error", e.getMessage(), null));
        }
    }

    @PreAuthorize("permitAll")
    @PostMapping("/letmeout")
    public ResponseEntity<LogoutResponse> logout(HttpServletResponse response) {
        System.out.println("Logout");
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(new LogoutResponse("success", "Logout successful"));
    }
}
