package com.final_project.eduflow.Services.Interfaces;

import com.final_project.eduflow.Data.DTO.MailEntity;


public interface IMailService {
     void sendAcceptedRequestToStudent(String studentMail);

     void sendRejectedRequestToStudent(String studentMail);

     void sendWaitingRequestToStaff(String staffMail);
}
