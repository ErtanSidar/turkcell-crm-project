package com.turkcell.analyticservice.persistence.subscription;

import com.turkcell.analyticservice.domain.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
