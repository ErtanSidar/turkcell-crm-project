package com.turkcell.planservice.controllers;

import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Subscription;
import com.turkcell.planservice.services.abstracts.SubscriptionService;
import com.turkcell.planservice.util.GenericResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public GetListResponse<SubscriptionResponse> getAllSubscriptions(@RequestParam int page, @RequestParam int size) {
        return subscriptionService.getAllSubscriptions(new PageInfo(page,size));
    }
    @GetMapping("/getOneSub")
    public SubscriptionResponse getOneSubscription(@RequestParam UUID id) {
        return subscriptionService.getOneSubs(id);
    }

    @PostMapping
    public GenericResponse<String> addSubscription(@RequestBody @Valid CreateSubscriptionRequest createSubscriptionRequest) {
        subscriptionService.createSubscription(createSubscriptionRequest);
        return GenericResponse.success("generic.subscription.created");
    }


    @DeleteMapping
    public GenericResponse<String> deleteSubscription(@RequestParam UUID id) {
        subscriptionService.delete(id);
        return GenericResponse.success("generic.subscription.deleted");
    }
}
