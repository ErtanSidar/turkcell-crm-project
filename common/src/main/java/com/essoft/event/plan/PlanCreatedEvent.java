package com.essoft.event.plan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanCreatedEvent {


    private String planName;

    private String planType;

    private double monthlyFee;

    private int internetQuota;


    private int callMinutes;


    private int smsCount;

    private String description;

}
