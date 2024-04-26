package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.Entities.Department;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.Data.View.AllRequestsForStaffView;
import com.final_project.eduflow.Data.View.ConcludedRequestView;
import com.final_project.eduflow.Data.View.WaitingRequestView;
import com.final_project.eduflow.DataAccess.AllRequestForStaffRepository;
import com.final_project.eduflow.DataAccess.ConcludedRequestRepository;
import com.final_project.eduflow.DataAccess.DepartmentRepository;
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.WaitingRequestsViewRepository;
import com.final_project.eduflow.Presentation.ResponseClasses.WaitingRequestBuilder;
import com.final_project.eduflow.Presentation.ResponseClasses.WaitingRequestsForStaff;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
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
    private final AllRequestForStaffRepository allRequestForStaffRepository;
    private final TeachingStaffRepository teachingStaffRepository;
    private final ConcludedRequestRepository concludedRequestRepository;

    @Autowired
    public WaitingRequestsViewController(WaitingRequestsViewRepository waitingRequestsViewRepository,
                                         StudentRepository studentRepository, DepartmentRepository departmentRepository, AllRequestForStaffRepository allRequestForStaffRepository, TeachingStaffRepository teachingStaffRepository, ConcludedRequestRepository concludedRequestRepository) {
        this.waitingRequestsViewRepository = waitingRequestsViewRepository;
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.allRequestForStaffRepository = allRequestForStaffRepository;
        this.teachingStaffRepository = teachingStaffRepository;
        this.concludedRequestRepository = concludedRequestRepository;
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
        List<WaitingRequestsForStaff> mappedRequests = allRequests.stream().map(requestView -> {
            Student student = studentRepository.findById(requestView.getStudentId()).orElse(null);
            Department department = null;
            if (student != null) {
                department = departmentRepository.findById(student.getDepartmentId()).orElse(null);
            }
            TeachingStaff teachingStaff = teachingStaffRepository.findById(student.getAdvisorId()).orElse(null);
            WaitingRequestBuilder builder = new WaitingRequestBuilder();

            return builder.setStudentId(requestView.getStudentId())
                    .setStudentName(student.getName() + " " + student.getSurname())
                    .setStudentMail(student.getEmail())
                    .setStudentDepartment(department.getName())
                    .setRequestTypeId(requestView.getRequestTypeId())
                    .setRequestTypeName(requestView.getRequestTypeName())
                    .setCurrent_index(requestView.getCurrent_index())
                    .setInformation(requestView.getInformation())
                    .setWhenCreated(requestView.getWhenCreated())
                    .setCurrentActorId(requestView.getCurrentActorId())
                    .setAddition(requestView.getAddition())
                    .setStatus(requestView.getStatus())
                    .setAdviserId(teachingStaff.getId())
                    .setAdviserName(teachingStaff.getName() + " " + teachingStaff.getSurname())
                    .build();
        }).collect(Collectors.toList());
        return ResponseEntity.ok(mappedRequests);
    }

    @PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty', 'Adviser', 'Bolum', 'Dekanlik', 'Danisman')")
    @GetMapping("/requestsForStaff")
    public ResponseEntity<List<AllRequestsForStaffView>> getRequestsForStaff(HttpServletRequest request) {
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long staffId = JwtUtil.getId(claims);


        return ResponseEntity.ok(allRequestForStaffRepository.findByActorId(staffId));
    }

    @PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty', 'Adviser', 'Bolum', 'Dekanlik', 'Danisman')")
    @GetMapping("/concludedRequests")
    public ResponseEntity<List<ConcludedRequestView>> getConcludedRequests(HttpServletRequest request) {
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long staffId = JwtUtil.getId(claims);

        return ResponseEntity.ok(concludedRequestRepository.findAllByActorId(staffId));
    }
}
