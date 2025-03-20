package com.turkcell.contractservice.dtos.requests;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateContractRequest {

    @NotNull(message = "Ticket ID cannot be null")
    private UUID ticketId;

    @NotNull(message = "Customer ID cannot be null")
    private UUID customerId;

    @Size(min = 3, max = 30, message = "Subject must be between 3 and 30 characters")
    private String subject;

    @Size(min = 3, max = 30, message = "Description must be between 3 and 30 characters")
    private String description;

    @Size(min = 3, max = 30, message = "Status must be between 3 and 30 characters")
    private String status;
}
