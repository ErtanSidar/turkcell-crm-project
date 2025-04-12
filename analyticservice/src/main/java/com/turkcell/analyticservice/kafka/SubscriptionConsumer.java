package com.turkcell.analyticservice.kafka;

import com.essoft.event.subscription.SubscriptionCreatedEvent;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.domain.entity.Subscription;
import com.turkcell.analyticservice.persistence.plan.PlanRepository;
import com.turkcell.analyticservice.persistence.subscription.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class SubscriptionConsumer {

    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;

    @KafkaListener(topics = "subscription-created",groupId = "create-subscription")
    public void consumeSubscription(SubscriptionCreatedEvent event) {
        Plan plan = planRepository.findById(event.getPlanId()).orElse(null);
        Subscription subscription = new Subscription();
        subscription.setCustomerId(event.getCustomerId());
        subscription.setStartDate(event.getStartDate());
        subscription.setStatus(event.getStatus());
        subscription.setPlan(plan);
        subscriptionRepository.save(subscription);
    }
}

