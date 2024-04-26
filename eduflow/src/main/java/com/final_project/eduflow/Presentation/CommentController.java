package com.final_project.eduflow.Presentation;

import com.final_project.eduflow.Config.JwtUtil;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.DataAccess.StaffCommentsRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Presentation.ResponseClasses.CommentRequest;
import com.final_project.eduflow.Presentation.ResponseClasses.GetCommentId;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.Entities.StaffComments;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;


@RestController
public class CommentController {

    private final StaffCommentsRepository staffCommentsRepository;
    private final TeachingStaffRepository teachingStaffRepository;

    public CommentController(StaffCommentsRepository staffCommentsRepository, TeachingStaffRepository teachingStaffRepository) {
        this.staffCommentsRepository = staffCommentsRepository;
        this.teachingStaffRepository = teachingStaffRepository;
    }

    // Save comments for a specific request
    // needs
    // @PARAMS: requestWhenCreated, requestStudentId, requestTypeId, comment
    // @RETURN: String

    //    Example request:
    //    {
    //        "requestWhenCreated": "2024-03-10T19:48:20.693+0300",
    //        "requestStudentId": "21896680",
    //        "requestTypeId": "1",
    //        "comment": "This is a comment"
    //    }
    @PreAuthorize("hasAnyAuthority('Danisman', 'Bolum', 'Dekanlik')")
    @PostMapping("/saveComment")
    public ResponseEntity<String> saveComments(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        Claims claims = JwtUtil.resolveClaims(request);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long staffId = JwtUtil.getId(claims);

        staffCommentsRepository.save(new StaffComments(commentRequest, staffId));

        return ResponseEntity.ok().body("Comment saved successfully");
    }

    // Get comments for a specific request
    // needs
    // @PARAMS: requestWhenCreated, requestStudentId, requestTypeId
    // @RETURN: List of comments

    //    Example request:
    //    {
    //        "requestWhenCreated": "2024-03-10T19:48:20.693+0300",
    //        "requestStudentId": "21896680",
    //        "requestTypeId": "1"
    //    }
    @PreAuthorize("hasAnyAuthority('Danisman', 'Bolum', 'Dekanlik')")
    @PostMapping("/getAllCommentsForRequest")
    public <T> ResponseEntity<T> getCommentsList(@RequestBody GetCommentId commentRequest) {
        List<StaffComments> comments = staffCommentsRepository.findByRequestWhenCreatedAndRequestStudentIdAndRequestTypeId(
                commentRequest.getRequestWhenCreated(),
                commentRequest.getRequestStudentId(),
                commentRequest.getRequestTypeId());
        if(comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Long> userIds = comments.stream()
                .map(StaffComments::getUserId)
                .toList();
        List<TeachingStaff> staff = teachingStaffRepository.findAllById(userIds);

        T responseBody = (T) convertToResponseType(comments, staff);

        return ResponseEntity.ok().body(responseBody);
    }

    public Map<String, List<Map<String, Object>>> convertToResponseType(List<StaffComments> comments, List<TeachingStaff> staff) {
        Map<Long, String> staffNameMap = staff.stream()
                .collect(Collectors.toMap(TeachingStaff::getId, s -> s.getName() + " " + s.getSurname()));

        List<Map<String, Object>> commentList = comments.stream().map(comment -> {
            Map<String, Object> commentMap = new HashMap<>();
            commentMap.put("staffComment", comment.getUserComment());
            commentMap.put("staffCommentTimePosted", comment.getTimePosted());

            String staffFullName = staffNameMap.get(comment.getUserId());
            if (staffFullName != null) {
                commentMap.put("staffName", staffFullName);
            }

            return commentMap;
        }).collect(Collectors.toList());

        Map<String, List<Map<String, Object>>> responseBody = new HashMap<>();
        responseBody.put("comments", commentList);

        return responseBody;
    }
}
