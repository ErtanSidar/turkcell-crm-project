package com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

    private String firstName;

    private String lastName;

    private String nationalityId;

    private LocalDate birthDate;
}
