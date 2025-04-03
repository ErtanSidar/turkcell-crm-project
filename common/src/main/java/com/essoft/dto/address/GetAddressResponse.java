package com.essoft.dto.address;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class GetAddressResponse {
    private UUID id;
    private String description;
    private String neighbourhood;
    private String houseNumber;
    private UUID districtId;
    private String street;
    private UUID countryId;
    private UUID cityId;
    private UUID customerId;
}
