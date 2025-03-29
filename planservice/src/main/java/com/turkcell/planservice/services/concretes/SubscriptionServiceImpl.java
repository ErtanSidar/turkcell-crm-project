package com.turkcell.planservice.services.concretes;

import com.essoft.dto.customer.GetCustomerResponse;
import com.turkcell.planservice.client.CustomerClient;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.UpdateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Plan;
import com.turkcell.planservice.entities.Subscription;
import com.turkcell.planservice.mappers.PlanMapper;
import com.turkcell.planservice.mappers.SubscriptionMapper;
import com.turkcell.planservice.repositories.SubscriptionRepository;
import com.turkcell.planservice.rules.PlanBusinessRules;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import com.turkcell.planservice.services.abstracts.PlanService;
import com.turkcell.planservice.services.abstracts.SubscriptionService;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class SubscriptionServiceImpl implements SubscriptionService {


    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionBusinessRules subscriptionBusinessRules;
    private final PlanBusinessRules planBusinessRules;
    private final AuditAwareImpl auditAware;
    private final CustomerClient customerClient;


    private final PlanService planService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionBusinessRules subscriptionBusinessRules, PlanBusinessRules planBusinessRules, AuditAwareImpl auditAware, CustomerClient customerClient, PlanService planService) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionBusinessRules = subscriptionBusinessRules;
        this.planBusinessRules = planBusinessRules;
        this.auditAware = auditAware;
        this.customerClient = customerClient;
        this.planService = planService;
    }


    @Override
    public SubscriptionResponse getOneSubs(UUID id) {
        log.info("Getting subscription by id: " + id);
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Subscription with id: " + id + " not found"));
        return SubscriptionMapper.INSTANCE.createSubscriptionResponseFromSubscription(subscription);
    }

    @Override
    public void createSubscription(CreateSubscriptionRequest createSubscriptionRequest) {
        // Check if the customer is already subscribed to the plan
        subscriptionBusinessRules.checkIfCustomerAlreadySubscribedToPlan(
                createSubscriptionRequest.getCustomerId(), createSubscriptionRequest.getPlanId());

        // Check if the plan exists
        subscriptionBusinessRules.checkIfPlanExistsForSubscription(createSubscriptionRequest.getPlanId().toString());

        // Fetch customer details using Feign Client
        GetCustomerResponse customerResponse = customerClient.findById(createSubscriptionRequest.getCustomerId());
        if (customerResponse == null) {
            throw new BusinessException("Customer not found with ID: " + createSubscriptionRequest.getCustomerId());
        }

        log.info("Customer found: " + customerResponse.getCustomerNumber());

        // Fetch plan details
        PlanResponse planResponse = planService.getOnePlan(createSubscriptionRequest.getPlanId());
        Plan plan = PlanMapper.INSTANCE.createPlanFromPlanResponse(planResponse);

        // Map request to Subscription entity
        Subscription subscription = SubscriptionMapper.INSTANCE.createSubscriptionFromCreateSubscriptionRequest(createSubscriptionRequest);
        subscription.setPlan(plan);
        subscription.setCustomerId(createSubscriptionRequest.getCustomerId()); // Set customer ID explicitly

        // Save subscription
        subscriptionRepository.save(subscription);

        log.info("Subscription created successfully with ID: " + subscription.getId());

    }

    @Override
    public void updateSubscription(UUID id, UpdateSubscriptionRequest updateSubscriptionRequest) {
        subscriptionBusinessRules.checkIfSubscriptionExists(id);

        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Subscription with id: " + id + " not found"));

        SubscriptionMapper.INSTANCE.updateSubscriptionFromRequest(subscription, updateSubscriptionRequest);

        if (updateSubscriptionRequest.getPlanId() != null) {
            planBusinessRules.checkIfPlanExists(updateSubscriptionRequest.getPlanId());
            PlanResponse planResponse = planService.getOnePlan(updateSubscriptionRequest.getPlanId());
            Plan plan = PlanMapper.INSTANCE.createPlanFromPlanResponse(planResponse);
            subscription.setPlan(plan);
        }

        subscriptionRepository.save(subscription);
    }


    @Override
    public GetListResponse<SubscriptionResponse> getAllSubscriptions(PageInfo pageInfo) {
        return ListResponse.get(pageInfo,
                subscriptionRepository,
                SubscriptionMapper.INSTANCE::createSubscriptionResponseFromSubscription);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        subscriptionBusinessRules.checkIfSubscriptionExists(id);
        log.info("Deleting subscription by id: " + id);
        subscriptionRepository.softDelete(id, LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
    }


}
