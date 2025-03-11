package com.turkcell.customerservice.services.dtos.responses.addressResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedAddressResponse {
    private UUID id;
    private String description;
    private String neighbourhood;
    private String houseNumber;
    private String street;
    private UUID districtId;
    private UUID countryId;
    private UUID cityId;
    private UUID customerId;
}
