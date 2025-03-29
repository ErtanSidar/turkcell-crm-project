package com.turkcell.planservice.dtos.subscriptiondtos.requests;

import lombok.*;

import java.util.UUID;
import jakarta.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubscriptionRequest {

    @NotNull(message = "Customer ID cannot be null")
    private UUID customerId;

    @NotBlank(message = "Start date cannot be empty")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Start date must be in the format YYYY-MM-DD")
    private String startDate;

    @NotBlank(message = "Status cannot be empty")
    @Size(max = 50, message = "Status must be at most 50 characters")
    private String status;

    @NotNull(message = "Plan ID cannot be null")
    private UUID planId;


}
