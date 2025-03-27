package com.turkcell.planservice.dtos.subscriptiondtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubscriptionRequest {

    @NotBlank(message = "Start date cannot be empty")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Start date must be in the format YYYY-MM-DD")
    private String startDate;

    @NotBlank(message = "Status cannot be empty")
    @Size(max = 50, message = "Status must be at most 50 characters")
    private String status;
}
