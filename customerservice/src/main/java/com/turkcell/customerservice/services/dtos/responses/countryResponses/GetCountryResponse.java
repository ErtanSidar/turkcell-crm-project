package com.turkcell.customerservice.services.dtos.responses.countryResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCountryResponse {
    private UUID id;
    private String name;
}
