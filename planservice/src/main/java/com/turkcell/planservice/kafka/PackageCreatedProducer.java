package com.turkcell.planservice.kafka;

import com.essoft.event.packageEntity.PackageCreatedEvent;
import com.essoft.event.plan.PlanCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PackageCreatedProducer {

    private final KafkaTemplate<String, PlanCreatedEvent> kafkaTemplate;


    public void sendMessage(PackageCreatedEvent packageCreatedEvent) {
        Message<PackageCreatedEvent> message = MessageBuilder.withPayload(packageCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC,"package-created").build();

        kafkaTemplate.send(message);

    }
}
