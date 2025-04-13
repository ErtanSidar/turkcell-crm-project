package com.turkcell.planservice.dtos.packagedtos.requests;

import jakarta.validation.constraints.*;
import lombok.*;


import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePackageRequest {

    @NotBlank(message = "Package name cannot be blank")
    @Size(min = 3, max = 50, message = "Package name must be between 3 and 50 characters")
    private String packageName;

    @NotBlank(message = "Package type cannot be blank")
    @Size(min = 3, max = 30, message = "Package type must be between 3 and 30 characters")
    private String packageType;

    @Min(value = 1, message = "Quota must be at least 1")
    @Max(value = 1000, message = "Quota cannot exceed 1000")
    private int quota;

    @Positive(message = "Price must be a positive value")
    private double price;

    @Min(value = 1, message = "Validity period must be at least 1 month")
    @Max(value = 36, message = "Validity period cannot exceed 36 months")
    private int validityPeriod;





}
