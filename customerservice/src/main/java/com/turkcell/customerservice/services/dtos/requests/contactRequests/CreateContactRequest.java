package com.turkcell.customerservice.services.dtos.requests.contactRequests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateContactRequest {
    @NotNull
    @Email
    private String email;

    @Nullable
    @Pattern(regexp = "^$|\\d{10}", message = "Home Phone must have 10 characters and contain only numbers.")
    private String homePhone;

    @NotNull
    @Pattern(regexp = "^5\\d{9}$", message = "Phone number must be written in (5xx)-xxx-xx-xx format")
    private String mobilePhone;

    @Nullable
    @Pattern(regexp = "^$|\\d{10}", message = "Fax must have 10 characters and contain only numbers.")
    private String fax;

    private UUID customerId;
}
