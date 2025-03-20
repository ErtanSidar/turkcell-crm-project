package com.turkcell.analyticservice.application.subscription.query.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Subscription;
import com.turkcell.analyticservice.persistence.subscription.SubscriptionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetSubscriptionByIdQuery implements Command<GetSubscriptionByIdItemDto> {
    private final UUID id;

    @Component
    @RequiredArgsConstructor
    public static class GetSubscriptionByIdQueryHandler
            implements Command.Handler<GetSubscriptionByIdQuery, GetSubscriptionByIdItemDto> {

        private final SubscriptionRepository subscriptionRepository;

        @Override
        public GetSubscriptionByIdItemDto handle(GetSubscriptionByIdQuery query) {
            Subscription subscription = subscriptionRepository.findById(query.getId())
                    .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + query.getId()));

            return new GetSubscriptionByIdItemDto(subscription.getId(), subscription.getCustomerId(),
                    subscription.getProduct(), subscription.getPlan(),
                    subscription.getStartDate(), subscription.getStatus());
        }
    }
}