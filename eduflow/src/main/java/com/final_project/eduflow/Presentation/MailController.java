package com.final_project.eduflow.Presentation;



import com.final_project.eduflow.Data.DTO.MailEntity;
import com.final_project.eduflow.Services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {

    @Autowired
    private final MailService mailService;

    MailController(MailService mailService)
    {
        this.mailService = mailService;
    }

    @PreAuthorize("hasAuthority('Student')")
    @PostMapping("/testmail")
    public void sendMail()
    {
        mailService.sendMail("huseyinborankuscu@gmail.com");
    }
}
