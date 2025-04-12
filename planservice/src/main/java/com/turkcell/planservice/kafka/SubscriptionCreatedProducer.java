package com.turkcell.planservice.kafka;

import com.essoft.event.plan.PlanCreatedEvent;
import com.essoft.event.subscription.SubscriptionCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionCreatedProducer {

    private final KafkaTemplate<String, PlanCreatedEvent> kafkaTemplate;


    public void sendMessage(SubscriptionCreatedEvent subscriptionCreatedEvent) {
        Message<SubscriptionCreatedEvent> message = MessageBuilder.withPayload(subscriptionCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC,"subscription-created").build();

        kafkaTemplate.send(message);

    }
}
