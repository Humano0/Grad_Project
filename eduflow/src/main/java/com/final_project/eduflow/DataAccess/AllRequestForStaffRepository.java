package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.View.AllRequestsForStaffView;
import com.final_project.eduflow.Data.View.IdClasses.AllRequestsId;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllRequestForStaffRepository extends JpaRepository<AllRequestsForStaffView, AllRequestsId>{

    List<AllRequestsForStaffView> findByActorId(long id);
    
} 