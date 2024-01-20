package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.DTO.AdvisorDashboardInfoEntity;
import com.final_project.eduflow.Data.DTO.StudentSideBarInfoEntity;
import com.final_project.eduflow.Data.Entities.Department;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.DataAccess.DepartmentRepository;
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
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
    private final StudentRepository studentRepository;
    private final TeachingStaffRepository teachingStaffRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public UserController(StudentRepository studentRepository, TeachingStaffRepository teachingStaffRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.teachingStaffRepository = teachingStaffRepository;
        this.departmentRepository = departmentRepository;
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/studentInfo")
    public ResponseEntity<StudentSideBarInfoEntity> getStudentInfo(HttpServletRequest request){
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // return 401 status
        }
        Long id = JwtUtil.getId(claims);
        return studentRepository.findById(id)
                .map(s -> ResponseEntity.ok(new StudentSideBarInfoEntity(s.getFirstname(), s.getLastname(), s.getId())))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("/advisorInfo")
    public ResponseEntity<AdvisorDashboardInfoEntity> getAdvisorInfo(HttpServletRequest request) {
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // return 401 status
        }
        Long id = JwtUtil.getId(claims);

        return studentRepository.findById(id)
                .map(student -> teachingStaffRepository.findById(student.getAdvisorId())
                        .map(advisor -> departmentRepository.findById(advisor.getDepartmentId())
                                .map(department -> {
                                    AdvisorDashboardInfoEntity advisorInfo = new AdvisorDashboardInfoEntity();
                                    advisorInfo.setFirstname(advisor.getFirstname());
                                    advisorInfo.setLastname(advisor.getLastname());
                                    advisorInfo.setDepartment(department.getName());
                                    return ResponseEntity.ok(advisorInfo);
                                })
                                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
                        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
