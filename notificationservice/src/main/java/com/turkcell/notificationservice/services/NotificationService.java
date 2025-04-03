package com.turkcell.notificationservice.services;

import jakarta.mail.MessagingException;

public interface NotificationService {
    public void sendNotification(String email, String title, String template) throws MessagingException;
}
