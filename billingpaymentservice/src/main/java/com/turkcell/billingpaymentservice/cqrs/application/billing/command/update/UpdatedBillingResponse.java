package com.turkcell.billingpaymentservice.cqrs.application.billing.command.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedBillingResponse {
    private UUID id;  // Güncellenen fatura kaydının ID'si
}
