package com.turkcell.analyticservice.domain.entity;

import io.github.ertansidar.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usages")
public class Usage extends BaseEntity<UUID> {

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

    @Field(name = "customer_id")
    private UUID customerId;

    @Field(name = "product")
    private Product product;

    @Field(name = "internet_used")
    private int internetUsed;

    @Field(name = "call_minutes_used")
    private int callMinutesUsed;

    @Field(name = "sms_used")
    private int smsUsed;

    @Field(name = "tv_hours_watched")
    private int tvHoursWatched;

}
