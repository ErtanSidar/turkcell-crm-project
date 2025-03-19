package com.turkcell.planservice.rules;

import com.turkcell.planservice.repositories.PlanRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlanBusinessRules {

    private final PlanRepository planRepository;

    public PlanBusinessRules(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public void checkIfPlanExists(UUID planId) {
        if (planRepository.findByIdAndDeletedAtIsNull(planId).isEmpty()) {
            throw new RuntimeException("Plan not found or deleted");
        }
    }

    public void checkIfPlanNameExists(String planName) {
        if (planRepository.existsByPlanName(planName)) {
            throw new RuntimeException("A plan with this name already exists");
        }
    }
}
