package com.turkcell.notificationservice.kafka;

import com.essoft.event.ticket.TicketCreatedEvent;
import com.turkcell.notificationservice.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
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

    @KafkaListener(topics = "ticket-created", groupId = "create-ticket")
    private void consume(TicketCreatedEvent event) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("customerName", event.getCustomerName());
        properties.put("subject", event.getSubject());
        properties.put("description", event.getDescription());
        properties.put("status", event.getStatus());

        Context context = new Context();
        context.setVariables(properties);

        String title = "Müşteri Destek Bilgilendirme";

        String template = templateEngine.process(event.getEmailTemplateName().getName(), context);
        notificationService.sendNotification("sidarertan3@gmail.com", title, template);
    }
}
