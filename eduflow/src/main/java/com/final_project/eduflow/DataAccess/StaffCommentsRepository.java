package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.final_project.eduflow.Data.Entities.StaffComments;
import com.final_project.eduflow.Data.Entities.IdClasses.StaffCommentsId;

public interface StaffCommentsRepository extends JpaRepository<StaffComments, StaffCommentsId> {
}