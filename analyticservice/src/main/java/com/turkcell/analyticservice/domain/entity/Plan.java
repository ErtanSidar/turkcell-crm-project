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
@Document(collection = "plans")
public class Plan extends BaseEntity<UUID> {

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

    @Field(name = "product")
    private Product product;

    @Field(name = "plan_name")
    private String planName;

    @Field(name = "plan_type")
    private String planType;

    @Field(name = "monthly_fee")
    private double monthlyFee;

    @Field(name = "internet_quota")
    private int internetQuota;

    @Field(name = "call_minutes")
    private int callMinutes;

    @Field(name = "sms_count")
    private int smsCount;

    @Field(name = "description")
    private String description;

}
