package com.turkcell.analyticservice.application.usage.rules;

import com.turkcell.analyticservice.domain.entity.Usage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsageBusinessRules {

    public void checkIfUsageIsValid(Usage usage) {
        if (usage == null) {
            throw new IllegalArgumentException("Usage cannot be null.");
        }

        if (usage.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null.");
        }

        if (usage.getProduct() == null) {
            throw new IllegalArgumentException("Product must be defined.");
        }

        if (usage.getInternetUsed() < 0) {
            throw new IllegalArgumentException("Internet usage cannot be negative.");
        }

        if (usage.getCallMinutesUsed() < 0) {
            throw new IllegalArgumentException("Call minutes cannot be negative.");
        }

        if (usage.getSmsUsed() < 0) {
            throw new IllegalArgumentException("SMS usage cannot be negative.");
        }

        if (usage.getTvHoursWatched() < 0) {
            throw new IllegalArgumentException("TV hours cannot be negative.");
        }
    }

    public void validateForCreate(Usage usage) {
        checkIfUsageIsValid(usage);

    }

    public void validateForUpdate(Usage usage) {
        checkIfUsageIsValid(usage);
    }
}