package com.turkcell.billingpaymentservice.cqrs.application.payment.query.getbyid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentByIdResponse {
    private UUID id;
    private UUID customerId;
    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;
}
