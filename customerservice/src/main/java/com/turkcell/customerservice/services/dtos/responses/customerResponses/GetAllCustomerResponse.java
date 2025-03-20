package com.turkcell.customerservice.services.dtos.responses.customerResponses;

import com.turkcell.customerservice.entities.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomerResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String nationalityId;

    private LocalDate birthDate;

    private String companyName;

    private String taxNumber;

    private CustomerType customerType;
}
