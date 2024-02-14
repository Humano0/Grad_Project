package com.final_project.eduflow.Services.Interfaces;

import com.final_project.eduflow.Data.DTO.MailEntity;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.Data.Entities.StudentRequests;

public interface IMailService {
     void sendMail(String mail, MailEntity mailEntity);

     void sendRequestAcceptedMail(Student student, StudentRequests studentRequest);

     void sendRequestRejectedMail(Student student, StudentRequests studentRequest);

     void sendRequestWaitingMail(Student student, StudentRequests studentRequest);

     void sendRequestWaitingMailToNextActor(Student student, StudentRequests studentRequest, String nextActorMail);

     void sendRequestWaitingMailToAdvisor(Student student, StudentRequests studentRequest, String advisorMail);

}
