package com.turkcell.notificationservice;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    JavaMailSenderImpl mailSender;
    private final NotificationProperties notificationProperties;

    @PostConstruct
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(notificationProperties.getEmail().host());
        mailSender.setPort(notificationProperties.getEmail().port());
        mailSender.setUsername(notificationProperties.getEmail().username());
        mailSender.setPassword(notificationProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");

    }

    public void sendMail(String email, String title, String template) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            message.setFrom(notificationProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(template, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);
    }
}
