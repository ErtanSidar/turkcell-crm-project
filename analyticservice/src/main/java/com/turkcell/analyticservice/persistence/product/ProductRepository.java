package com.turkcell.analyticservice.persistence.product;

import com.turkcell.analyticservice.domain.entity.Product;

import java.util.UUID;

public class ProductRepository extends JpaRepository<Product, UUID> {
}
