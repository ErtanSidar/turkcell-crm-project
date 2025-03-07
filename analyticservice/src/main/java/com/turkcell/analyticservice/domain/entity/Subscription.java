package com.turkcell.analyticservice.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collation = "subscriptions")
public class Subscription {

    @Id
    @Field(name = "id")
    private UUID id;

    @Field(name = "customer_id")
    private UUID customerId;

    @Field(name = "product")
    private Product product;

    @Field(name = "plan")
    private Plan plan;

    @Field(name = "start_date")
    private String startDate;

    @Field(name = "status")
    private String status;

}
