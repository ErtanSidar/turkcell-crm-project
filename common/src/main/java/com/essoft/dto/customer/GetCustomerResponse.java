package com.essoft.dto.customer;

import com.essoft.dto.address.GetAddressResponse;
import com.essoft.dto.contact.GetContactResponse;
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
    private String customerType;
    private List<GetContactResponse> contacts;
    private List<GetAddressResponse> addresses;
}
