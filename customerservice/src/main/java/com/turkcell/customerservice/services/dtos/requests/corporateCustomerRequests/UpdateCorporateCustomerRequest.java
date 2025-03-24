package com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests;

import com.turkcell.customerservice.entities.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {

    private String companyName;

    private String taxNumber;

    private CustomerType customerType;
}
