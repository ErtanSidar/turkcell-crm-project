package com.turkcell.billingpaymentservice.cqrs.application.payment.command.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedPaymentResponse {
    private UUID id;
    private UUID customerId;
    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;
    private UUID billingId;
}

