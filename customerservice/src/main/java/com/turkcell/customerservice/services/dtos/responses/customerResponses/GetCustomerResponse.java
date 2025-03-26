package com.turkcell.customerservice.services.dtos.responses.customerResponses;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.entities.CustomerType;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.GetAddressResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.GetContactResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class GetCustomerResponse {

    private UUID id;
    private String customerNumber;
    private CustomerType customerType;
    private List<GetContactResponse> contacts;
    private List<GetAddressResponse> addresses;
}
