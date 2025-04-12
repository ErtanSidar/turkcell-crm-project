package com.turkcell.planservice.kafka;


import com.essoft.event.plan.PlanCreatedEvent;
import com.essoft.event.product.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreatedProducer {

    private final KafkaTemplate<String, PlanCreatedEvent> kafkaTemplate;


    public void sendMessage(ProductCreatedEvent productCreatedEvent) {
        Message<ProductCreatedEvent> message = MessageBuilder.withPayload(productCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC,"product-created").build();

        kafkaTemplate.send(message);

    }
}
