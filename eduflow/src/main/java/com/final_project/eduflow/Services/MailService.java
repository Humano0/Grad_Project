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

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendAcceptedRequestToStudent(String studentMail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(studentMail);
        message.setSubject("Request Accepted");
        message.setText("Your request has been accepted.");
        mailSender.send(message);
    }

    @Override
    public void sendRejectedRequestToStudent(String studentMail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(studentMail);
        message.setSubject("Request Rejected");
        message.setText("Your request has been rejected.");
        mailSender.send(message);
    }

    @Override
    public void sendWaitingRequestToStaff(String staffMail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(staffMail);
        message.setSubject("New Request");
        message.setText("New request waiting for approval.");
        mailSender.send(message);
    }


//    @Override
//    public void sendMail(String mail) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(fromEmail);
//        message.setTo(mail);
//        message.setSubject("test xd");
//        message.setText("selam xd");
//        mailSender.send(message);
//    }

}

