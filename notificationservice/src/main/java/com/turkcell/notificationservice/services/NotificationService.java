package com.turkcell.notificationservice.services;

public interface NotificationService {
    public void sendNotification(String email, String title, String template);
}
