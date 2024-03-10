//package com.final_project.eduflow.Presentation;
//
//
//
//import com.final_project.eduflow.Data.DTO.MailEntity;
//import com.final_project.eduflow.Services.MailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/mail")
//public class MailController {
//
//    @Autowired
//    private MailService mailService;
//
//    @PostMapping("/send/{mail}")
//    public void sendMail(@PathVariable String mail, @RequestBody MailEntity mailStructure)
//    {
//        mailService.sendMail(mail, mailStructure);
//    }
//}
