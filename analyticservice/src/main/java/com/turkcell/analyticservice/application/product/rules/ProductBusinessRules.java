package com.turkcell.analyticservice.application.product.rules;

import com.turkcell.analyticservice.domain.entity.Product;
import io.github.ertansidar.exception.type.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class ProductBusinessRules {

    public void isProductValid(Product product) {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new BusinessException("Product name cannot be null or empty.");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new BusinessException("Description cannot be null or empty.");
        }
    }
}