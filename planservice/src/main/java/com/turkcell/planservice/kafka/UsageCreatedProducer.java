package com.turkcell.planservice.kafka;

import com.essoft.event.plan.PlanCreatedEvent;
import com.essoft.event.subscription.SubscriptionCreatedEvent;
import com.essoft.event.usage.UsageCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsageCreatedProducer {

    private final KafkaTemplate<String, PlanCreatedEvent> kafkaTemplate;

    public void sendMessage(UsageCreatedEvent usageCreatedEvent) {
        Message<UsageCreatedEvent> message = MessageBuilder.withPayload(usageCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC,"usage-created").build();

        kafkaTemplate.send(message);

    }
}
