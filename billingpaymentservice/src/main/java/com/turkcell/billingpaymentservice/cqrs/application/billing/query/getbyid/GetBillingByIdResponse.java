package com.turkcell.billingpaymentservice.cqrs.application.billing.query.getbyid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBillingByIdResponse {
    private UUID id;
    private String billingDetails;
}
