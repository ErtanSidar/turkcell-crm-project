package com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses;

import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerResponse extends GetCustomerResponse {

    private String companyName;

    private String taxNumber;
}
