package com.turkcell.billingpaymentservice.cqrs.application.billing.query.getlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListBillingResponse {
    private UUID id;
    private double amount;
    private String status;
}
