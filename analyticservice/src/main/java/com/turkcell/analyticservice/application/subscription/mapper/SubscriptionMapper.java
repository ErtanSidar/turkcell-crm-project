package com.turkcell.analyticservice.application.subscription.mapper;

import com.turkcell.analyticservice.application.subscription.command.create.CreateSubscriptionCommand;
import com.turkcell.analyticservice.application.subscription.command.create.CreatedSubscriptionResponse;
import com.turkcell.analyticservice.application.subscription.command.update.UpdateSubscriptionCommand;
import com.turkcell.analyticservice.application.subscription.command.update.UpdatedSubscriptionResponse;
import com.turkcell.analyticservice.domain.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);


    Subscription createSubscriptionFromCreateCommand(CreateSubscriptionCommand command);
    CreatedSubscriptionResponse createSubscriptionResponseFromSubscription(Subscription subscription);


    @Mapping(target = "id", ignore = true)
    void updateSubscriptionFromUpdateCommand(UpdateSubscriptionCommand command, @MappingTarget Subscription subscription);
    UpdatedSubscriptionResponse createUpdatedSubscriptionResponse(Subscription subscription);
}