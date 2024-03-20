package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.Entities.Department;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.Data.View.WaitingRequestView;
import com.final_project.eduflow.DataAccess.DepartmentRepository;
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.WaitingRequestsViewRepository;
import com.final_project.eduflow.Presentation.ResponseClasses.WaitingRequestsForStaff;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WaitingRequestsViewController {
    private final WaitingRequestsViewRepository waitingRequestsViewRepository;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public WaitingRequestsViewController(WaitingRequestsViewRepository waitingRequestsViewRepository,
                                         StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.waitingRequestsViewRepository = waitingRequestsViewRepository;
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    // List waiting requests for staff
    @PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty', 'Adviser', 'Bolum', 'Dekanlik', 'Danisman')")
    @GetMapping("/listWaitingRequestsForStaff")
    public ResponseEntity<List<WaitingRequestsForStaff>> getWaitingRequestsForStaff(HttpServletRequest request) {
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long staffId = JwtUtil.getId(claims);
        List<WaitingRequestView> allRequests = waitingRequestsViewRepository.findByCurrentActorId(staffId);
        Student student = studentRepository.findById(allRequests.get(0).getStudentId()).orElse(null);
        Department department = departmentRepository.findById(student.getDepartmentId()).orElse(null);
        List<WaitingRequestsForStaff> mappedRequests = allRequests.stream().map(requestView -> {
            WaitingRequestsForStaff staffRequest = new WaitingRequestsForStaff();
            staffRequest.setStudentId(requestView.getStudentId());
            staffRequest.setRequestTypeId(requestView.getRequestTypeId());
            staffRequest.setRequestTypeName(requestView.getRequestTypeName());
            staffRequest.setCurrent_index(requestView.getCurrent_index());
            staffRequest.setInformation(requestView.getInformation());
            staffRequest.setWhenCreated(requestView.getWhenCreated());
            staffRequest.setCurrentActorId(requestView.getCurrentActorId());
            staffRequest.setStudentName(student.getName() + " " + student.getSurname());
            staffRequest.setStudentMail(student.getEmail());
            staffRequest.setStudentDepartment(department.getName());
            staffRequest.setStatus(requestView.getStatus());
            return staffRequest;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(mappedRequests);
    }
}
