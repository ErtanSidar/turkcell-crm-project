package com.turkcell.planservice.events.dtos.customerdtos.response;

import com.turkcell.planservice.events.entities.CustomerType;
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
public class GetCustomerResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String nationalityId;

    private LocalDate birthDate;

    private String companyName;

    private String taxNumber;

    private CustomerType customerType;
}
