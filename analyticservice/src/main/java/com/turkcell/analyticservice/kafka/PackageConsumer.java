package com.turkcell.analyticservice.kafka;

import com.essoft.event.packageEntity.PackageCreatedEvent;
import com.essoft.event.plan.PlanCreatedEvent;
import com.turkcell.analyticservice.domain.entity.Package;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.packages.PackageRepository;
import com.turkcell.analyticservice.persistence.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class PackageConsumer {

    private final PackageRepository packageRepository;
    private final ProductRepository productRepository;

    @KafkaListener(topics = "package-created",groupId = "create-package")
    private void consumePlan(PackageCreatedEvent event) {
        Product product = productRepository.findById(event.getProductId()).orElseThrow();
        Package pack = new Package();
        pack.setPackageName(event.getPackageName());
        pack.setPackageType(event.getPackageType());
        pack.setQuota(event.getQuota());
        pack.setPrice(event.getPrice());
        pack.setValidityPeriod(event.getValidityPeriod());
        pack.setProduct(product);
        packageRepository.save(pack);
    }
}
