package com.turkcell.billingpaymentservice.cqrs.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
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

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
