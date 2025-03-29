package com.essoft.dto.customer;

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
