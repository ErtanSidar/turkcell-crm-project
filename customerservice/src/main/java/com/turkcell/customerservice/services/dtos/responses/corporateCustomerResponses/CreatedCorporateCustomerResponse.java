package com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCorporateCustomerResponse {

    private String companyName;

    private String taxNumber;

    private String customerNumber;
}
