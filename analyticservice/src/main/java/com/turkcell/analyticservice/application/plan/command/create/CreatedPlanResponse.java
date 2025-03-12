package com.turkcell.analyticservice.application.plan.command.create;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedPlanResponse {
    private UUID id;
    private String planName;
    private String planType;
    private double monthlyFee;
    private int internetQuota;
    private int callMinutes;
    private int smsCount;
    private String description;
}