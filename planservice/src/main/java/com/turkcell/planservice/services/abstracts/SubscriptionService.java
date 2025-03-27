package com.turkcell.planservice.services.abstracts;

import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.UpdateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Subscription;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;


public interface SubscriptionService {
    GetListResponse<SubscriptionResponse> getAllSubscriptions(PageInfo pageInfo);

    void deleteById(UUID id);

    SubscriptionResponse getOneSubs(UUID id);

    void createSubscription(CreateSubscriptionRequest createSubscriptionRequest);

    void updateSubscription(UUID id, UpdateSubscriptionRequest updateSubscriptionRequest);
}
