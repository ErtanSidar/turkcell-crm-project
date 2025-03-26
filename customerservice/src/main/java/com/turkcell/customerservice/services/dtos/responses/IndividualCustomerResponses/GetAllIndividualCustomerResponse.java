package com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIndividualCustomerResponse {
    private String firstName;
    private String lastName;
    private String nationalityId;
    private LocalDate birthDate;
}
