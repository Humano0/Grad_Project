package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.DTO.AdvisorDashboardInfoEntity;
import com.final_project.eduflow.Data.DTO.StudentSideBarInfoEntity;
import com.final_project.eduflow.DataAccess.AdvisorInfoViewRepository;
import com.final_project.eduflow.DataAccess.DepartmentRepository;
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Services.AdvisorInfoService;
import com.final_project.eduflow.Services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
    @SuppressWarnings("unused")
    private final StudentRepository studentRepository;
    @SuppressWarnings("unused")
    private final TeachingStaffRepository teachingStaffRepository;
    @SuppressWarnings("unused")
    private final DepartmentRepository departmentRepository;
    private final AdvisorInfoService advisorInfoService;
    private final UserService userService;

    @Autowired
    public UserController(StudentRepository studentRepository, TeachingStaffRepository teachingStaffRepository, DepartmentRepository departmentRepository, AdvisorInfoViewRepository advisorInfoViewRepository, AdvisorInfoService advisorInfoService, UserService userService) {
        this.studentRepository = studentRepository;
        this.teachingStaffRepository = teachingStaffRepository;
        this.departmentRepository = departmentRepository;
        this.advisorInfoService = advisorInfoService;
        this.userService = userService;
    }

    // Sidebar student info
    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/studentInfo")
    public ResponseEntity<StudentSideBarInfoEntity> getStudentInfo(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);
        return ResponseEntity.ok(userService.getStudentSideBarInfo(id));
    }

    // Dashboard advisor info
    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/advisorInfo")
    public ResponseEntity<AdvisorDashboardInfoEntity> getAdvisorInfo(HttpServletRequest request) {
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = JwtUtil.getId(claims);
        return ResponseEntity.ok(advisorInfoService.getAdvisorDashboardInfoByStudentId(id));
    }
}
