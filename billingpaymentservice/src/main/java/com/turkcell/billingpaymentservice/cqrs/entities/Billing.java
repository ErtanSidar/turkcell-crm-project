package com.turkcell.billingpaymentservice.cqrs.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "billings")
public class Billing extends BaseEntity<UUID> {

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "subscription_id")
    private UUID subscriptionId;

    @Column(name = "billing_period")
    private String billingPeriod;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "status")
    private String status;

    @OneToOne
    private Payment payment;

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
