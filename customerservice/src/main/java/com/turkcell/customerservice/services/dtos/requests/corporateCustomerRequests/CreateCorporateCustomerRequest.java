package com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {

    private String companyName;

    private String taxNumber;

}
