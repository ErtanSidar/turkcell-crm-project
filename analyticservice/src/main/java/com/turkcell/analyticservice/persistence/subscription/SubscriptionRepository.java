package com.turkcell.analyticservice.persistence.subscription;

import com.turkcell.analyticservice.domain.entity.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SubscriptionRepository extends MongoRepository<Subscription, UUID> {
}
