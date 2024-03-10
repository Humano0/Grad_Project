//package com.final_project.eduflow.Services;
//
//
//
//
//import com.final_project.eduflow.Data.DTO.MailEntity;
//import com.final_project.eduflow.Services.Interfaces.IMailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MailService implements IMailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("$(spring.mail.username)")
//    private String fromMail;
//
//    public void sendMail(String mail, MailEntity mailEntity)
//    {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("fromMail");
//        simpleMailMessage.setSubject(mailEntity.getSubject());
//        simpleMailMessage.setText(mailEntity.getMessage());
//        simpleMailMessage.setTo(mail);
//
//        mailSender.send(simpleMailMessage);
//    }
//
//}
//
