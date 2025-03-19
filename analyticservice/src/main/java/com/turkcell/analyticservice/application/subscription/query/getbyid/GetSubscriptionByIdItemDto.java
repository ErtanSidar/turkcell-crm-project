package com.turkcell.analyticservice.application.subscription.query.getbyid;

import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.domain.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetSubscriptionByIdItemDto {

    private UUID id;
    private UUID customerId;
    private Product product;
    private Plan plan;
    private String startDate;
    private String status;
}