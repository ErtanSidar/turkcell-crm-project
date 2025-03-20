package com.turkcell.billingpaymentservice.cqrs.application.billing.command.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedBillingResponse {
    private UUID id;
}
