package com.turkcell.analyticservice.kafka;


import com.essoft.event.usage.UsageCreatedEvent;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.domain.entity.Usage;
import com.turkcell.analyticservice.persistence.product.ProductRepository;
import com.turkcell.analyticservice.persistence.usage.UsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class UsageConsumer {

    private final UsageRepository usageRepository;
    private final ProductRepository productRepository;

    @KafkaListener(topics = "usage-created",groupId = "create-usage")
    private void consumeUsage(UsageCreatedEvent usageCreatedEvent) {

        Product product = productRepository.findById(usageCreatedEvent.getProductId()).orElseThrow();
        Usage usage = new Usage();
        usage.setCustomerId(usageCreatedEvent.getCustomerId());
        usage.setProduct(product);
        usage.setInternetUsed(usageCreatedEvent.getInternetUsed());
        usage.setCallMinutesUsed(usageCreatedEvent.getCallMinutesUsed());
        usage.setSmsUsed(usageCreatedEvent.getSmsUsed());
        usage.setTvHoursWatched(usageCreatedEvent.getTvHoursWatched());
        usageRepository.save(usage);
    }

}
