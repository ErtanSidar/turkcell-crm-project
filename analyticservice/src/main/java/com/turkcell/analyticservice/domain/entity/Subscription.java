package com.turkcell.analyticservice.domain.entity;

import io.github.ertansidar.entities.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subscriptions")
public class Subscription extends BaseEntity<UUID> {

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

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
