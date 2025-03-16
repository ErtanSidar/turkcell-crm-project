package com.turkcell.planservice.dtos.packagedtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import jakarta.validation.constraints.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePackageRequest {

    @NotBlank(message = "Package name cannot be empty")
    @Size(max = 100, message = "Package name must be at most 100 characters")
    private String packageName;

    @NotBlank(message = "Package type cannot be empty")
    private String packageType;

    @Positive(message = "Quota must be a positive number")
    private int quota;

    @Positive(message = "Price must be a positive number")
    private double price;

    @Positive(message = "Validity period must be a positive number")
    private int validityPeriod;


}
