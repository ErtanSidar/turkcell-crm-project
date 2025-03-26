package com.turkcell.analyticservice.application.subscription.rules;

import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.domain.entity.Subscription;
import com.turkcell.analyticservice.persistence.subscription.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SubscriptionBusinessRules {

    private final SubscriptionRepository subscriptionRepository;

    public void checkIfSubscriptionIsValid(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null.");
        }

        if (subscription.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null.");
        }

        if (subscription.getProduct() == null) {
            throw new IllegalArgumentException("Product must be defined.");
        }

        if (subscription.getPlan() == null) {
            throw new IllegalArgumentException("Plan must be defined.");
        }

        if (subscription.getStartDate() == null || subscription.getStartDate().isBlank()) {
            throw new IllegalArgumentException("Start date must be provided.");
        }

        if (subscription.getStatus() == null || subscription.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status must be provided.");
        }
    }

    public void checkIfAlreadySubscribed(UUID customerId, Product product) {
        if (subscriptionRepository.existsByCustomerIdAndProduct(customerId, product)) {
            throw new IllegalStateException("Customer is already subscribed to this product.");
        }
    }

    public void checkIfSubscriptionCanBeCancelled(String status) {
        if (!Objects.equals(status, "ACTIVE")) {
            throw new IllegalStateException("Only active subscriptions can be cancelled.");
        }
    }

    public void validateForCreate(Subscription subscription) {
        checkIfSubscriptionIsValid(subscription);
        checkIfAlreadySubscribed(subscription.getCustomerId(), subscription.getProduct());
    }
    public void validateForUpdate(Subscription subscription) {
        checkIfSubscriptionIsValid(subscription);
    }
}