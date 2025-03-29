package com.turkcell.customersupportservice.services.dtos.requests;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class CreateTicketRequest {
    @NotEmpty(message = "Subject cannot be empty")
    private String subject;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Customer ID cannot be null")
    private UUID customerId;

    @NotEmpty(message = "Status cannot be empty")
    private String status;

}
