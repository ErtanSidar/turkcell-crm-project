package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Plan;
import com.turkcell.planservice.entities.Subscription;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class SubscriptionServiceImpl implements SubscriptionService {


    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionBusinessRules subscriptionBusinessRules;


    private final PlanService planService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionBusinessRules subscriptionBusinessRules, PlanService planService) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionBusinessRules = subscriptionBusinessRules;
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
        subscriptionBusinessRules.checkIfCustomerAlreadySubscribedToPlan(
                createSubscriptionRequest.getCustomerId(), createSubscriptionRequest.getPlanId());
        subscriptionBusinessRules.checkIfPlanExistsForSubscription(
                createSubscriptionRequest.getPlanId().toString());

        log.info("Creating subscription for customer: " + createSubscriptionRequest.getCustomerId());


        Subscription subscription = SubscriptionMapper.INSTANCE.toSubscription(createSubscriptionRequest);

        PlanResponse planResponse = planService.getOnePlan(createSubscriptionRequest.getPlanId());  // Burada getOnePlanResponse metodu PlanResponse döndürmeli.
        Plan plan = PlanMapper.INSTANCE.createPlanFromPlanResponse(planResponse);

        subscription.setPlan(plan);

        subscriptionRepository.save(subscription);

        log.info("Subscription created successfully with ID: " + subscription.getId());

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
        log.info("Deleting subscription by id: " + id);
        subscriptionRepository.deleteById(id);
    }


}
