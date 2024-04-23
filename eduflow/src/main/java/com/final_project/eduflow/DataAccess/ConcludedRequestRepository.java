package com.final_project.eduflow.DataAccess;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.View.ConcludedRequestView;
import com.final_project.eduflow.Data.View.IdClasses.ConcludedRequestsId;


public interface ConcludedRequestRepository extends JpaRepository<ConcludedRequestView, ConcludedRequestsId>{
    
    ArrayList<ConcludedRequestView> findByStudentId(long studentId);

    ArrayList<ConcludedRequestView> findByStudentIdAndRequestTypeId(long studentId, int requestTypeId);

    //ArrayList<ConcludedRequestView> findByActorId(long actorId);
}
