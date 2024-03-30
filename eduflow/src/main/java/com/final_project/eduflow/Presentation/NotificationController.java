package com.final_project.eduflow.Presentation;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
    @MessageMapping("/notification")
    @SendTo("/request/notification/{userId}")
    public void handleNotification() {
        // handle notification message
    }

    @SubscribeMapping("/request/notification/{userId}")
    public void subscribeToNotification(Long userId){

    }
}
