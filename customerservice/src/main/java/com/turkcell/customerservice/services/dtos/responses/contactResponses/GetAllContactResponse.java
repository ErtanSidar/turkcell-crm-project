package com.turkcell.customerservice.services.dtos.responses.contactResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllContactResponse {
    private UUID id;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String fax;
    private UUID customerId;
}
