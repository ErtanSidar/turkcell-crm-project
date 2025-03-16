package com.turkcell.planservice.rules;

import com.turkcell.planservice.repositories.SubscriptionRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SubscriptionBusinessRules {

    private final PlanBusinessRules planBusinessRules;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionBusinessRules(PlanBusinessRules planBusinessRules,
                                     SubscriptionRepository subscriptionRepository) {

        this.planBusinessRules = planBusinessRules;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void checkIfSubscriptionExists(UUID subscriptionId) {
        if (subscriptionRepository.findByIdAndDeletedAtIsNull(subscriptionId).isEmpty()) {
            throw new RuntimeException("Subscription not found or deleted");
        }
    }

    public void checkIfPlanExistsForSubscription(String planName) {
        planBusinessRules.checkIfPlanNameExists(planName);
    }

    public void checkIfCustomerAlreadySubscribedToPlan(UUID customerId, UUID planId) {
        if (subscriptionRepository.existsByCustomerIdAndPlanId(customerId, planId)) {
            throw new RuntimeException("Customer is already subscribed to this plan");
        }
    }

    public void checkIfPlanCanBeDeleted(UUID planId) {
        if (subscriptionRepository.existsByPlanId(planId)) {
            throw new RuntimeException("Cannot delete plan because there are active subscriptions");
        }
    }

//    public void checkIfProductCanBeDeleted(UUID productId) {
//        if (subscriptionRepository.existsByProductId(productId)) {
//            throw new RuntimeException("Cannot delete product because there are active subscriptions");
//        }
//    }
}
