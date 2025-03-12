package com.turkcell.analyticservice.application.subscription.command.create;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.domain.entity.Product;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedSubscriptionResponse {
    private UUID id;
    private UUID customerId;
    private Product product;
    private Plan plan;
    private String startDate;
    private String status;
}