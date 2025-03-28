package com.turkcell.customerservice.services.dtos.requests.addressRequests;

import com.turkcell.customerservice.entities.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {
    @NotBlank(message = "Address description can not be empty")
    @NotNull
    @Size(max = 200, message = "Address description may has maximum 200 characters")
    private String description;

    @NotBlank(message = "Neighbourhood name can not be empty")
    @NotNull
    @Size(max = 30, message = "Neighbourhood may has maximum 20 characters")
    private String neighbourhood;

    @NotBlank(message = "House number name can not be empty")
    @NotNull
    @Size(max = 30, message = "House number may has maximum 20 characters")
    private String houseNumber;

    @NotBlank(message = "Street name can not be empty")
    @NotNull
    @Size(max = 30, message = "Street may has maximum 20 characters")
    private String street;

    private AddressType addressType;

    private UUID countryId;

    private UUID cityId;

    private UUID districtId;

    private UUID customerId;
}