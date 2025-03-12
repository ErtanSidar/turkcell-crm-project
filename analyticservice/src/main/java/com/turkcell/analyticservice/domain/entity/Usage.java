package com.turkcell.analyticservice.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "usage")
public class Usage {

    @Id
    @Field(name = "id")
    private UUID id;

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
