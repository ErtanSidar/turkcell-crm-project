package com.turkcell.customerservice.services.dtos.requests.districtRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDistrictRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    private UUID cityId;
}
