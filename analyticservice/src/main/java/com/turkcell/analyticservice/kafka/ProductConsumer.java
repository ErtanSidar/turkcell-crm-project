package com.turkcell.analyticservice.kafka;

import com.essoft.event.product.ProductCreatedEvent;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class ProductConsumer {

    private final ProductRepository productRepository;

    @KafkaListener(topics = "product-created",groupId = "create-product")
    public void consumeProduct(ProductCreatedEvent productCreatedEvent){
        Product product = new Product();
        product.setProductName(productCreatedEvent.getProductName());
        product.setDescription(productCreatedEvent.getDescription());
        product.setProductType(productCreatedEvent.getProductType());
//        product.setPlanId(createProductRequest.getPlanId());
//        product.setSubscriptionId(createProductRequest.getSubscriptionId());
        productRepository.save(product);

    }
}
