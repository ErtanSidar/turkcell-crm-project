package com.turkcell.customerservice.services.dtos.requests.cityRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    private UUID countryId;
}
