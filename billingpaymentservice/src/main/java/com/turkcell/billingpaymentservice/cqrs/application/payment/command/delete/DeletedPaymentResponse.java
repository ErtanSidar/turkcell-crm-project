package com.turkcell.billingpaymentservice.cqrs.application.payment.command.delete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedPaymentResponse {
    private UUID id;
}
