package com.turkcell.analyticservice.application.subscription.query.list;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Subscription;
import com.turkcell.analyticservice.persistence.subscription.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

public class GetListSubscriptionQuery implements Command<List<GetListSubscriptionItemDto>> {

    @Component
    @RequiredArgsConstructor
    public static class GetListSubscriptionQueryHandler
            implements Command.Handler<GetListSubscriptionQuery, List<GetListSubscriptionItemDto>> {

        private final SubscriptionRepository subscriptionRepository;

        @Override
        public List<GetListSubscriptionItemDto> handle(GetListSubscriptionQuery query) {
            List<Subscription> subscriptions = subscriptionRepository.findAll();


            return subscriptions.stream()
                    .map(sbscrpt -> new GetListSubscriptionItemDto(
                            sbscrpt.getId(),
                            sbscrpt.getCustomerId(),
                            sbscrpt.getProduct(),
                            sbscrpt.getPlan(),
                            sbscrpt.getStartDate(),
                            sbscrpt.getStatus()
                    ))
                    .toList();
        }
    }
}