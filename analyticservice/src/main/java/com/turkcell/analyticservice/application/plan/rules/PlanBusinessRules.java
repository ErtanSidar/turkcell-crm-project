package com.turkcell.analyticservice.application.plan.rules;

import com.turkcell.analyticservice.domain.entity.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanBusinessRules {

    public void checkIfPlanExists(Plan plan) {
        if (plan == null) {
            throw new IllegalArgumentException("Plan does not exist.");
        }
    }


    public void checkIfPlanNameIsValid(Plan plan) {
        if (plan.getPlanName() == null || plan.getPlanName().trim().isEmpty()) {
            throw new IllegalArgumentException("Plan name cannot be null or empty.");
        }
    }


    public void checkIfPlanTypeIsValid(Plan plan) {
        if (plan.getPlanType() == null || plan.getPlanType().trim().isEmpty()) {
            throw new IllegalArgumentException("Plan type cannot be null or empty.");
        }
    }


    public void checkIfMonthlyFeeIsValid(Plan plan) {
        if (plan.getMonthlyFee() < 0) {
            throw new IllegalArgumentException("Monthly fee cannot be negative.");
        }
    }


    public void checkIfQuotaValuesAreValid(Plan plan) {
        if (plan.getInternetQuota() < 0) {
            throw new IllegalArgumentException("Internet quota cannot be negative.");
        }
        if (plan.getCallMinutes() < 0) {
            throw new IllegalArgumentException("Call minutes cannot be negative.");
        }
        if (plan.getSmsCount() < 0) {
            throw new IllegalArgumentException("SMS count cannot be negative.");
        }
    }


    public void checkIfPlanCanBeDeleted(Plan plan) {
        if ("DEFAULT".equalsIgnoreCase(plan.getPlanType())) {
            throw new IllegalArgumentException("Default plans cannot be deleted.");
        }
    }

    public void checkIfPlanIsValid(Plan plan) {
        checkIfPlanNameIsValid(plan);
        checkIfPlanTypeIsValid(plan);
        checkIfMonthlyFeeIsValid(plan);
        checkIfQuotaValuesAreValid(plan);
    }
}