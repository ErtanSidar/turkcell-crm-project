package com.turkcell.planservice.mappers;

import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    SubscriptionResponse createSubscriptionResponseFromSubscription(Subscription subscription);

//    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "plan.id", source = "planId")
    Subscription toSubscription(CreateSubscriptionRequest request);
}
