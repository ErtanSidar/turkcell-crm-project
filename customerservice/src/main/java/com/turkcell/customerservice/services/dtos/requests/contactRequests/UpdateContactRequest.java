package com.turkcell.customerservice.services.dtos.requests.contactRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactRequest {
    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Home Phone must have 10 characters and contain only numbers.")
    private String homePhone;

    @NotNull
    @Pattern(regexp = "^5\\d{9}$", message = "Phone number must be written in (5xx)-xxx-xx-xx format")
    private String mobilePhone;

    @Pattern(regexp = "^\\d{10}$", message = "Fax must have 10 characters and contain only numbers.")
    private String fax;

    private UUID customerId;
}
