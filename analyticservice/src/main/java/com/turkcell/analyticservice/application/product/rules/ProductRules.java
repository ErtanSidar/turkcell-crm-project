package com.turkcell.analyticservice.application.product.rules;

import com.turkcell.analyticservice.domain.entity.Product;

public class ProductRules {

    public static boolean isProductValid(Product product) {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        return true;
    }
}