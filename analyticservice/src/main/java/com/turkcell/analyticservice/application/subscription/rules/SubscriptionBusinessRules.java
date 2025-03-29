package com.turkcell.analyticservice.application.subscription.rules;

import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.domain.entity.Subscription;
import com.turkcell.analyticservice.persistence.subscription.SubscriptionRepository;
import io.github.ertansidar.exception.type.BusinessException;
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
            throw new BusinessException("Subscription cannot be null.");
        }

        if (subscription.getCustomerId() == null) {
            throw new BusinessException("Customer ID cannot be null.");
        }

        if (subscription.getProduct() == null) {
            throw new BusinessException("Product must be defined.");
        }

        if (subscription.getPlan() == null) {
            throw new BusinessException("Plan must be defined.");
        }

        if (subscription.getStartDate() == null || subscription.getStartDate().isBlank()) {
            throw new BusinessException("Start date must be provided.");
        }

        if (subscription.getStatus() == null || subscription.getStatus().isBlank()) {
            throw new BusinessException("Status must be provided.");
        }
    }

    public void checkIfAlreadySubscribed(UUID customerId, Product product) {
        if (subscriptionRepository.existsByCustomerIdAndProduct(customerId, product)) {
            throw new BusinessException("Customer is already subscribed to this product.");
        }
    }

    public void checkIfSubscriptionCanBeCancelled(String status) {
        if (!"ACTIVE".equalsIgnoreCase(status)) {
            throw new BusinessException("Only active subscriptions can be cancelled.");
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
