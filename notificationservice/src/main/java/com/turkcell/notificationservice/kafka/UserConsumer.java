package com.turkcell.notificationservice.kafka;

import com.essoft.event.user.UserCreatedEvent;
import com.turkcell.notificationservice.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class UserConsumer {

    private final NotificationService notificationService;
    private final SpringTemplateEngine templateEngine;


    @KafkaListener(topics = "user-created", groupId = "create-user")
    private void consume(UserCreatedEvent event) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", event.getUsername());
        properties.put("email", event.getEmail());

        Context context = new Context();
        context.setVariables(properties);

        String title = "Kullanıcı Sisteme Kayıt Bildirimi";

        String template = templateEngine.process(event.getEmailTemplateName().getName(), context);
        notificationService.sendNotification(event.getEmail(), title, template);
    }

}
