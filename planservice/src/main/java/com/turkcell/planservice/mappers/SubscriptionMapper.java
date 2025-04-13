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
    Subscription createSubscriptionFromSubscriptionResponse(SubscriptionResponse subscriptionResponse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plan", ignore = true)
    @Mapping(target = "customerId", source = "customerId")
    Subscription createSubscriptionFromCreateSubscriptionRequest(CreateSubscriptionRequest request);

    @Mapping(target = "plan", ignore = true) // Convert separately
    void updateSubscriptionFromRequest(@MappingTarget Subscription subscription, UpdateSubscriptionRequest request);
}
