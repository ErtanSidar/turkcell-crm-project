package com.turkcell.notificationservice.kafka;

import com.essoft.event.user.UserCreatedEvent;
import com.turkcell.notificationservice.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class UserConsumer {

    private final NotificationService notificationService;
    private final SpringTemplateEngine templateEngine;

    @Bean
    public Consumer<UserCreatedEvent> userCreatedFunction() {
        return message -> {
            Map<String, Object> properties = new HashMap<>();
            properties.put("username", message.getUsername());
            properties.put("email", message.getEmail());

            Context context = new Context();
            context.setVariables(properties);

            String title = "Kullanıcı Sisteme Kayıt Bildirimi";

            String template = templateEngine.process(message.getEmailTemplateName().getName(), context);
            notificationService.sendNotification(message.getEmail(), title, template);
        };
    }
}
