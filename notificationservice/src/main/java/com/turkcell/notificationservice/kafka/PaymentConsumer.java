package com.turkcell.notificationservice.kafka;

import com.essoft.event.ticket.TicketCreatedEvent;
import com.turkcell.notificationservice.services.NotificationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class PaymentConsumer {

    private final NotificationService notificationService;
    private final SpringTemplateEngine templateEngine;

    @KafkaListener(topics = "payment-created", groupId = "create-payment")
    private void consume(TicketCreatedEvent event) throws MessagingException {
        Map<String, Object> properties = new HashMap<>();
        properties.put("customerName", event.getCustomerName());
        properties.put("subject", event.getSubject());
        properties.put("description", event.getDescription());
        properties.put("status", event.getStatus());

        Context context = new Context();
        context.setVariables(properties);

        String title = "Müşteri Destek Bilgilendirme";

        String template = templateEngine.process(event.getEmailTemplateName().getName(), context);
        notificationService.sendNotification(event.getEmail(), title, template);
    }
}
