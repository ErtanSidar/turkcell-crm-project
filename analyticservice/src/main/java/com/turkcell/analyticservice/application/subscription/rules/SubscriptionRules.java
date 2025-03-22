package com.turkcell.analyticservice.application.subscription.rules;

import com.turkcell.analyticservice.domain.entity.Subscription;

public class SubscriptionRules {

    public static boolean isSubscriptionValid(Subscription subscription) {
        if (subscription.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null.");
        }
        if (subscription.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (subscription.getPlan() == null) {
            throw new IllegalArgumentException("Plan cannot be null.");
        }
        return true;
    }
}
