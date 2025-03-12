package com.turkcell.analyticservice.application.subscription.command.update;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.subscription.mapper.SubscriptionMapper;
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
public class UpdateSubscriptionCommand implements Command<UpdatedSubscriptionResponse> {
    private UUID id;
    private UUID customerId;
    private Product product;
    private Plan plan;
    private String startDate;
    private String status;

    @Component
    @RequiredArgsConstructor
    public static class UpdateSubscriptionCommandHandler implements Command.Handler<UpdateSubscriptionCommand, UpdatedSubscriptionResponse> {
        private final SubscriptionRepository subscriptionRepository;

        @Override
        public UpdatedSubscriptionResponse handle(UpdateSubscriptionCommand command) {
            Subscription subscription = subscriptionRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("Subscription not found"));
            SubscriptionMapper subscriptionMapper = SubscriptionMapper.INSTANCE;
            subscriptionMapper.updateSubscriptionFromUpdateCommand(command, subscription);
            subscriptionRepository.save(subscription);
            return subscriptionMapper.createUpdatedSubscriptionResponse(subscription);
        }
    }
}
