package com.turkcell.planservice.dtos.plandtos.requests;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlanRequest {

    @NotBlank(message = "Plan name cannot be empty")
    @Size(max = 100, message = "Plan name must be at most 100 characters")
    private String planName;

    @NotBlank(message = "Plan type cannot be empty")
    private String planType;

    @Positive(message = "Monthly fee must be a positive number")
    private double monthlyFee;

    @PositiveOrZero(message = "Internet quota cannot be negative")
    private int internetQuota;

    @PositiveOrZero(message = "Call minutes cannot be negative")
    private int callMinutes;

    @PositiveOrZero(message = "SMS count cannot be negative")
    private int smsCount;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

}
