package com.turkcell.customerservice.services.dtos.responses.cityResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCityResponse {
    private UUID id;
    private String name;
    private UUID countryId;
}
