package com.turkcell.planservice.kafka;

import com.essoft.event.plan.PlanCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanCreatedProducer {

    private final KafkaTemplate<String, PlanCreatedEvent> kafkaTemplate;


    public void sendMessage(PlanCreatedEvent planCreatedEvent) {
        Message<PlanCreatedEvent> message = MessageBuilder.withPayload(planCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC,"plan-created").build();

        kafkaTemplate.send(message);

    }
}
