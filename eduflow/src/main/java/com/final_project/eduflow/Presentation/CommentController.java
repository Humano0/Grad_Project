package com.final_project.eduflow.Presentation;

import org.springframework.web.bind.annotation.RestController;

import com.final_project.eduflow.Data.Entities.StaffComments;
import com.final_project.eduflow.DataAccess.CommentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;



@RestController
public class CommentController {

    private final CommentRepository commentRepository;


    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/saveComment")
    public StaffComments saveComments(@RequestBody StaffComments comment) {
        //TODO: process POST request
        
        commentRepository.save(comment);

        return comment;
    }

    @GetMapping("/getComment/{requestStudentId}/{whenCreated}")
    public ResponseEntity<ArrayList<StaffComments>> getCommentsList(@RequestParam int requestStudentId, @RequestParam String whenCreated) {
        return ResponseEntity.ok().body(new ArrayList<StaffComments>());
    }
    
    
}
