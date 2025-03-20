package com.turkcell.billingpaymentservice.cqrs.application.billing.command.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private LocalDateTime dueDate;
    private String status;
}
