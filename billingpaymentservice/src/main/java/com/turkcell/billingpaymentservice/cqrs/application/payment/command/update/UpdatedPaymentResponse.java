package com.turkcell.billingpaymentservice.cqrs.application.payment.command.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedPaymentResponse {
    private UUID id;
    private Double updatedAmount;
    private String updatedPaymentMethod;
}
