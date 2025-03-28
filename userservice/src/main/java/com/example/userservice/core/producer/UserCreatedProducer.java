package com.example.userservice.core.producer;

import com.essoft.event.user.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreatedProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public void sendMessage(UserCreatedEvent userCreatedEvent) {
        Message<UserCreatedEvent> message = MessageBuilder.withPayload(userCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "user-created")
                .build();
        kafkaTemplate.send(message);
    }
}
