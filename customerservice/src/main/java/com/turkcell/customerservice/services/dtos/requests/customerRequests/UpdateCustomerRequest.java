package com.turkcell.customerservice.services.dtos.requests.customerRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    private String firstName;

    private String lastName;

    private String nationalityId;

    private String birthDate;

    private String companyName;

    private String taxNumber;
}
