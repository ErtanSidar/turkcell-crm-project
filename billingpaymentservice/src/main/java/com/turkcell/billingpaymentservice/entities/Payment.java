package com.turkcell.billingpaymentservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity<UUID> {

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "billing_id")
    private Billing billing;

}
