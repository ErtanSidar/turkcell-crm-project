package com.turkcell.analyticservice.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collation = "plans")
public class Plan {

    @Id
    @Field(name = "id")
    private UUID id;

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
