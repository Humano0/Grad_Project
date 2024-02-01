package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.View.WaitingRequestView;
import com.final_project.eduflow.DataAccess.WaitingRequestsViewRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WaitingRequestsViewController {
    private final WaitingRequestsViewRepository waitingRequestsViewRepository;

    @Autowired
    public WaitingRequestsViewController(WaitingRequestsViewRepository waitingRequestsViewRepository) {
        this.waitingRequestsViewRepository = waitingRequestsViewRepository;
    }

    // List waiting requests for staff
    @PreAuthorize("hasAnyAuthority('Advisor', 'Head_of_Department', 'Dean_of_Faculty')")
    @GetMapping("/listWaitingRequestsForStaff")
    public ResponseEntity<List<WaitingRequestView>> getWaitingRequestsForStaff(HttpServletRequest request) {
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long staffId = JwtUtil.getId(claims);
        List<WaitingRequestView> allRequests = waitingRequestsViewRepository.findByCurrentActorId(staffId);
        return ResponseEntity.ok(allRequests);
    }
}
