package com.turkcell.planservice.rules;

import com.turkcell.planservice.repositories.UsageRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsageBusinessRules {

    private final ProductBusinessRules productBusinessRules;
    private final UsageRepository usageRepository;

    public UsageBusinessRules(ProductBusinessRules productBusinessRules, UsageRepository usageRepository) {
        this.productBusinessRules = productBusinessRules;
        this.usageRepository = usageRepository;
    }

    public void checkIfUsageExists(UUID usageId) {
        if (usageRepository.findByIdAndDeletedAtIsNull(usageId).isEmpty()) {
            throw new RuntimeException("Usage record not found or deleted");
        }
    }

    public void checkIfUsageIsValid(String planName, String packageName) {
        productBusinessRules.checkIfProductHasValidPlanAndPackage(planName, packageName);
    }
}
