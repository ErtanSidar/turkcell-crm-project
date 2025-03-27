package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.UpdateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Plan;
import com.turkcell.planservice.entities.Subscription;
import com.turkcell.planservice.events.dtos.customerdtos.response.GetCustomerResponse;
import com.turkcell.planservice.feign.CustomerClient;
import com.turkcell.planservice.mappers.PlanMapper;
import com.turkcell.planservice.mappers.SubscriptionMapper;
import com.turkcell.planservice.repositories.SubscriptionRepository;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import com.turkcell.planservice.services.abstracts.PlanService;
import com.turkcell.planservice.services.abstracts.SubscriptionService;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {


    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionBusinessRules subscriptionBusinessRules;
    private final CustomerClient customerClient;
    private final PlanService planService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionBusinessRules subscriptionBusinessRules, CustomerClient customerClient, PlanService planService) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionBusinessRules = subscriptionBusinessRules;
        this.customerClient = customerClient;
        this.planService = planService;
    }


    @Override
    public SubscriptionResponse getOneSubs(UUID id) {

        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Subscription with id: " + id + " not found"));
        return SubscriptionMapper.INSTANCE.createSubscriptionResponseFromSubscription(subscription);
    }

    @Override
    public void createSubscription(CreateSubscriptionRequest createSubscriptionRequest) {
        subscriptionBusinessRules.checkIfCustomerAlreadySubscribedToPlan(
                createSubscriptionRequest.getCustomerId(), createSubscriptionRequest.getPlanId());
        subscriptionBusinessRules.checkIfPlanExistsForSubscription(
                createSubscriptionRequest.getPlanId().toString());


        GetCustomerResponse customerResponse;
        try {
            customerResponse = customerClient.findById(createSubscriptionRequest.getCustomerId());
        } catch (Exception e) {
            throw new RuntimeException("Customer Service is not available or customer not found: " + e.getMessage());
        }


        Subscription subscription = SubscriptionMapper.INSTANCE.createSubscriptionFromCreateSubscriptionRequest(createSubscriptionRequest);

        PlanResponse planResponse = planService.getOnePlan(createSubscriptionRequest.getPlanId());
        Plan plan = PlanMapper.INSTANCE.createPlanFromPlanResponse(planResponse);

        subscription.setCustomerId(customerResponse.getId());
        subscription.setPlan(plan);

        subscriptionRepository.save(subscription);



    }

    @Override
    public void updateSubscription(UUID id, UpdateSubscriptionRequest updateSubscriptionRequest) {
        subscriptionBusinessRules.checkIfSubscriptionExists(id);

        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Subscription with id: " + id + " not found"));


        SubscriptionMapper.INSTANCE
                .updateSubscriptionFromUpdateSubscriptionRequest(subscription,updateSubscriptionRequest);

        subscriptionRepository.save(subscription);
    }


    @Override
    public GetListResponse<SubscriptionResponse> getAllSubscriptions(PageInfo pageInfo) {
        return ListResponse.get(pageInfo,
                subscriptionRepository,
                SubscriptionMapper.INSTANCE::createSubscriptionResponseFromSubscription);
    }

    @Override
    public void deleteById(UUID id) {
        subscriptionBusinessRules.checkIfSubscriptionExists(id);

        subscriptionRepository.softDelete(id, LocalDateTime.now(),AuditServiceImpl.USER);
    }


}
