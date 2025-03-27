package com.turkcell.planservice.repositories;


import com.turkcell.planservice.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {


    boolean existsByCustomerIdAndPlanId(UUID customerId, UUID planId);
    boolean existsByPlanId(UUID planId);
//    List<Subscription> findByCustomerId(UUID customerId);
    Optional<Subscription> findByIdAndDeletedAtIsNull(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Subscription e SET e.deletedAt = :deletedAt, e.deletedBy = :deletedBy WHERE e.id = :id")
    void softDelete(@Param("id") UUID id, @Param("deletedAt") LocalDateTime deletedAt, @Param("deletedBy") String deletedBy);
}
