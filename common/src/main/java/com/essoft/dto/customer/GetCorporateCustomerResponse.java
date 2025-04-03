package com.essoft.dto.customer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class GetCorporateCustomerResponse extends GetCustomerResponse {

    private String companyName;

    private String taxNumber;
}
