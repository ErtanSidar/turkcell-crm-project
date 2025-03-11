package com.turkcell.customerservice.services.dtos.requests.addressRequests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {
    @Size(max = 200, message = "Address description may has maximum 200 characters")
    private String description;

    @Size(max = 30, message = "Neighbourhood may has maximum 20 characters")
    private String neighbourhood;

    @Size(max = 30, message = "Neighbourhood may has maximum 20 characters")
    private String houseNumber;

    @Size(max = 30, message = "Neighbourhood may has maximum 20 characters")
    private String street;

    private UUID countryId;

    private UUID cityId;

    private UUID districtId;

    private UUID customerId;
}
