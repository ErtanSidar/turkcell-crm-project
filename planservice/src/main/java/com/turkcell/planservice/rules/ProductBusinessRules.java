package com.turkcell.planservice.rules;

import com.turkcell.planservice.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductBusinessRules {


    private final PlanBusinessRules planBusinessRules;
    private final ProductRepository productRepository;

    public ProductBusinessRules(PlanBusinessRules planBusinessRules, ProductRepository productRepository) {
        this.planBusinessRules = planBusinessRules;
        this.productRepository = productRepository;
    }

    public void checkIfProductExists(UUID productId) {
        if (productRepository.findByIdAndDeletedAtIsNull(productId).isEmpty()) {
            throw new RuntimeException("Product not found or deleted");
        }
    }

    public void checkIfProductHasValidPlanAndPackage(String planName) {
        planBusinessRules.checkIfPlanNameExists(planName);
    }
}
