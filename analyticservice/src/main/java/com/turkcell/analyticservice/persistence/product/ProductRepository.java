package com.turkcell.analyticservice.persistence.product;

import com.turkcell.analyticservice.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
}
