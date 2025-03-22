package com.turkcell.analyticservice.application.usage.rules;

import com.turkcell.analyticservice.domain.entity.Usage;

public class UsageRules {

    public static boolean isUsageValid(Usage usage) {
        if (usage.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null.");
        }
        if (usage.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (usage.getInternetUsed() < 0 || usage.getCallMinutesUsed() < 0 ||
                usage.getSmsUsed() < 0 || usage.getTvHoursWatched() < 0) {
            throw new IllegalArgumentException("Usage values cannot be negative.");
        }
        return true;
    }
}