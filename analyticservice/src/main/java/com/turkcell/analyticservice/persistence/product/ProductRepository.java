package com.turkcell.analyticservice.persistence.product;

import com.turkcell.analyticservice.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
