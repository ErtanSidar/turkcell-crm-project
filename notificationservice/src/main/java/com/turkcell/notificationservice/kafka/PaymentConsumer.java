package com.turkcell.notificationservice.kafka;

import com.essoft.event.payment.PaymentCreatedEvent;
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
    private void consume(PaymentCreatedEvent event) throws MessagingException {
        Map<String, Object> properties = new HashMap<>();
        properties.put("customerName", event.getCustomerName());
        properties.put("amount", event.getAmount());
        properties.put("paymentDate", event.getPaymentDate());
        properties.put("paymentMethod", event.getPaymentMethod());
        properties.put("status", event.getStatus());

        Context context = new Context();
        context.setVariables(properties);

        String title = "Müşteri Odeme Bilgilendirme";

        String template = templateEngine.process(event.getEmailTemplateName().getName(), context);
        notificationService.sendNotification(event.getCustomerEmail(), title, template);
    }
}
