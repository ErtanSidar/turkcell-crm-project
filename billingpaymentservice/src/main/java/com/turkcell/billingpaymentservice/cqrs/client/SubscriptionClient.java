package com.turkcell.billingpaymentservice.cqrs.client;

import com.essoft.dto.subscription.GetSubscriptionFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "planservice", url = "${plan.service.url}")
public interface SubscriptionClient {

    @GetMapping("api/v1/subscriptions({id}")
    GetSubscriptionFeignResponse findById(@PathVariable UUID id);
}
