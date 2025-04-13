package com.turkcell.planservice.dtos.productdtos.requests;

import lombok.*;

import java.util.UUID;
import jakarta.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequest {
    @NotBlank(message = "Product name cannot be empty")
    @Size(max = 100, message = "Product name must be at most 100 characters")
    private String productName;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotBlank(message = "Product type cannot be empty")
    private String productType;

    @NotNull(message = "Plan ID cannot be null")
    private UUID planId;

    @NotNull(message = "Subscription ID cannot be null")
    private UUID subscriptionId;
}
