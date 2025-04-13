package com.turkcell.billingpaymentservice.cqrs.core;

import com.essoft.event.payment.PaymentCreatedEvent;
import com.essoft.event.ticket.TicketCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCreatedProducer {

    private final KafkaTemplate<String, TicketCreatedEvent> kafkaTemplate;

    public void sendMessage(PaymentCreatedEvent paymentCreatedEvent) {
        Message<PaymentCreatedEvent> message = MessageBuilder.withPayload(paymentCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "payment-created")
                .build();
        kafkaTemplate.send(message);
    }
}
