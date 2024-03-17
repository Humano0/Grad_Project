package com.final_project.eduflow.Services.Interfaces;

import com.final_project.eduflow.Data.DTO.MailEntity;


public interface IMailService {
     void sendMail(String mail);

      void sendRequestAcceptedMailtoStudent(String studentMail);

     void sendRequestRejectedMailtoStudent(String studentMail);

     void sendRequestWaitingMailtoStaff(String staffMail);
}
