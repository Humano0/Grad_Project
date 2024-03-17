package com.final_project.eduflow.Services;




import com.final_project.eduflow.Data.DTO.MailEntity;
import com.final_project.eduflow.Services.Interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("$(spring.mail.username)")
    private String fromMail;

    @Override
    public void sendMail(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(mail);
        message.setSubject("mail subject");
        message.setText("mail text");
        mailSender.send(message);
    }

    @Override
    public void sendRequestAcceptedMailtoStudent(String studentMail) {

    }

    @Override
    public void sendRequestRejectedMailtoStudent(String studentMail) {

    }

    @Override
    public void sendRequestWaitingMailtoStaff(String staffMail) {

    }
}

