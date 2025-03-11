package com.turkcell.billingpaymentservice.cqrs.persistance.billing;

import com.turkcell.billingpaymentservice.cqrs.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillingRepository extends JpaRepository<Billing, UUID> {
}
