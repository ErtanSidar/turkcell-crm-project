package com.turkcell.planservice.dtos.plandtos.responses;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanResponse {
    private UUID id;
    private String planName;
    private String planType;
    private double monthlyFee;
    private int internetQuota;
    private int callMinutes;
    private int smsCount;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
}
