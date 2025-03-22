package com.turkcell.analyticservice.application.plan.rules;

import com.turkcell.analyticservice.domain.entity.Plan;

public class PlanRules {

    public static boolean isPlanValid(Plan plan) {
        if (plan.getPlanName() == null || plan.getPlanName().isEmpty()) {
            throw new IllegalArgumentException("Plan name cannot be null or empty.");
        }
        if (plan.getPlanType() == null || plan.getPlanType().isEmpty()) {
            throw new IllegalArgumentException("Plan type cannot be null or empty.");
        }
        if (plan.getMonthlyFee() < 0) {
            throw new IllegalArgumentException("Monthly fee cannot be negative.");
        }

        return true;
    }
}