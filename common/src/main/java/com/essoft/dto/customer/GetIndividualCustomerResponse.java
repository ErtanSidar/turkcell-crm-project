package com.essoft.dto.customer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class GetIndividualCustomerResponse extends GetCustomerResponse {
    private String firstName;
    private String lastName;
    private String nationalityId;
    private LocalDate birthDate;
}
