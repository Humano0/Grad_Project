package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.Entities.StaffComments;
import com.final_project.eduflow.Data.Entities.IdClasses.StaffCommentsId;
import java.time.OffsetDateTime;

public interface CommentRepository extends JpaRepository<StaffComments, StaffCommentsId>{

    StaffComments findByRequestStudentIdAndRequestWhenCreated(int requestStudentId , OffsetDateTime whenCreated);
    
} 