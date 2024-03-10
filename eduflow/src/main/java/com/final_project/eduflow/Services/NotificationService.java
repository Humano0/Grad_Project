package com.final_project.eduflow.Services;

import com.final_project.eduflow.Services.Interfaces.INotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(Long userId, String message) {
        messagingTemplate.convertAndSend("/request/notification" + userId, message);
    }
}