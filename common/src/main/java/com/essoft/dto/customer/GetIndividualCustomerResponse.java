package com.essoft.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerResponse extends GetCustomerResponse {
    private String firstName;
    private String lastName;
    private String nationalityId;
    private LocalDate birthDate;
}
