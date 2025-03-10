package com.turkcell.analyticservice.persistence.subscription;

import com.turkcell.analyticservice.domain.entity.Subscription;

import java.util.UUID;

public class SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
