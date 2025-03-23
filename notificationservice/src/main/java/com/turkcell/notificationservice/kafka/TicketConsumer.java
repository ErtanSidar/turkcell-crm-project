package com.turkcell.notificationservice.kafka;

import com.essoft.event.ticket.TicketCreatedEvent;
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
public class TicketConsumer {

    private final NotificationService notificationService;
    private final SpringTemplateEngine templateEngine;

    @Bean
    public Consumer<TicketCreatedEvent> ticketCreatedFunction() {
        return message -> {
            Map<String, Object> properties = new HashMap<>();
            properties.put("customerName", message.getCustomerId().toString());
            properties.put("subject", message.getSubject());
            properties.put("description", message.getDescription());
            properties.put("status", message.getStatus());

            Context context = new Context();
            context.setVariables(properties);

            String title = "Müşteri Destek Bilgilendirme";

            String template = templateEngine.process(message.getEmailTemplateName().getName(), context);
            notificationService.sendNotification("sidarertan3@gmail.com", title, template);
        };
    }
}
