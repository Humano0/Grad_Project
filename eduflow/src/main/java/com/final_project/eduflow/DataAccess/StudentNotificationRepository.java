package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.IdClasses.StudentNotificationId;
import com.final_project.eduflow.Data.Entities.StudentNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentNotificationRepository extends JpaRepository<StudentNotification, StudentNotificationId> {
}
