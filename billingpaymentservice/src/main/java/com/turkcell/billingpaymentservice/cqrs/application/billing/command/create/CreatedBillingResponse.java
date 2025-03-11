package com.turkcell.billingpaymentservice.cqrs.application.billing.command.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedBillingResponse {
    private UUID id;
    private UUID customerId;
    private UUID subscriptionId;
    private String billingPeriod;
    private Double totalAmount;
    private String dueDate;
    private String status;
}
