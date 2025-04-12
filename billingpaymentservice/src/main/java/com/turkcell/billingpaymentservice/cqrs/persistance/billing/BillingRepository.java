package com.turkcell.billingpaymentservice.cqrs.persistance.billing;

import com.turkcell.billingpaymentservice.cqrs.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BillingRepository extends JpaRepository<Billing, UUID> {
    @Transactional
    @Modifying
    @Query("UPDATE Billing e SET e.deletedAt = :deletedAt, e.deletedBy = :deletedBy WHERE e.id = :id")
    void softDelete(@Param("id") UUID id, @Param("deletedAt") LocalDateTime deletedAt, @Param("deletedBy") String deletedBy);

}
