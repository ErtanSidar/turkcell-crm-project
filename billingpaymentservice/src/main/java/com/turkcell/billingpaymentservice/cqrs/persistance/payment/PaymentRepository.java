package com.turkcell.billingpaymentservice.cqrs.persistance.payment;

import com.turkcell.billingpaymentservice.cqrs.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{
}
