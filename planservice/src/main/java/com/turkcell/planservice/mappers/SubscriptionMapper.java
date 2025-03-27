package com.turkcell.planservice.mappers;

import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.UpdateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    SubscriptionResponse createSubscriptionResponseFromSubscription(Subscription subscription);
    
    @Mapping(target = "plan.id", source = "planId")
    Subscription createSubscriptionFromCreateSubscriptionRequest(CreateSubscriptionRequest request);

    Subscription updateSubscriptionFromUpdateSubscriptionRequest(@MappingTarget Subscription subscription, UpdateSubscriptionRequest request);
}
