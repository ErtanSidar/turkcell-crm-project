package com.turkcell.analyticservice.application.product.rules;

import com.turkcell.analyticservice.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductBusinessRules {

    public void isProductValid(Product product) {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
    }
}