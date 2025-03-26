package com.turkcell.analyticservice.application.subscription.command.create;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.subscription.mapper.SubscriptionMapper;
import com.turkcell.analyticservice.application.subscription.rules.SubscriptionBusinessRules;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.domain.entity.Subscription;
import com.turkcell.analyticservice.persistence.subscription.SubscriptionRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubscriptionCommand implements Command<CreatedSubscriptionResponse> {
    private UUID customerId;
    private Product product;
    private Plan plan;
    private String startDate;
    private String status;

    @Component
    @RequiredArgsConstructor
    public static class CreateSubscriptionCommandHandler implements Command.Handler<CreateSubscriptionCommand, CreatedSubscriptionResponse> {
        private final SubscriptionRepository subscriptionRepository;
        private final SubscriptionBusinessRules subscriptionBusinessRules;

        @Override
        public CreatedSubscriptionResponse handle(CreateSubscriptionCommand command) {
            SubscriptionMapper subscriptionMapper = SubscriptionMapper.INSTANCE;
            Subscription subscription = subscriptionMapper.createSubscriptionFromCreateCommand(command);
            subscriptionBusinessRules.validateForCreate(subscription);
            subscriptionRepository.save(subscription);
            return subscriptionMapper.createSubscriptionResponseFromSubscription(subscription);
        }
    }
}