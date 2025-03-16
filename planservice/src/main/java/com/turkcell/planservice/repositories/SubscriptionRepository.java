package com.turkcell.planservice.repositories;


import com.turkcell.planservice.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {


    boolean existsByCustomerIdAndPlanId(UUID customerId, UUID planId);
    boolean existsByPlanId(UUID planId);
//    List<Subscription> findByCustomerId(UUID customerId);
    Optional<Subscription> findByIdAndDeletedAtIsNull(UUID id);
}
