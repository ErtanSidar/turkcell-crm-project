package com.turkcell.customersupportservice.coreBusiness.producer;

import com.essoft.event.ticket.TicketCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketCreatedProducer {

    private final KafkaTemplate<String, TicketCreatedEvent> kafkaTemplate;

    public void sendMessage(TicketCreatedEvent ticketCreatedEvent) {
        Message<TicketCreatedEvent> message = MessageBuilder.withPayload(ticketCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "ticket-created")
                .build();
        kafkaTemplate.send(message);
    }
}
