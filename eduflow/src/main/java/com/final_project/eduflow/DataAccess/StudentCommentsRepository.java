package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.IdClasses.StudentCommentsId;
import com.final_project.eduflow.Data.Entities.StudentComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCommentsRepository extends JpaRepository<StudentComments, StudentCommentsId>{
}
