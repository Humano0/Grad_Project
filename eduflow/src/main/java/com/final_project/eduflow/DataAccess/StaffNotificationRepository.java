package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.IdClasses.StaffNotificationId;
import com.final_project.eduflow.Data.Entities.StaffNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffNotificationRepository extends JpaRepository<StaffNotification, StaffNotificationId> {
}
