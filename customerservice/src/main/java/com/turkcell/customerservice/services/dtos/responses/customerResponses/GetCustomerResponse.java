package com.turkcell.customerservice.services.dtos.responses.customerResponses;

import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.entities.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
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

    private List<Contact> contacts;
}
