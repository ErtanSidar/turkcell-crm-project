package com.turkcell.customersupportservice.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateTicketRequest {
    @NotEmpty(message = "Subject cannot be empty")
    @NotNull
    @NotBlank
    private String subject;

    @NotEmpty(message = "Description cannot be empty")
    @NotNull
    @NotBlank
    private String description;

    @NotEmpty(message = "Status cannot be empty")
    @NotNull
    @NotBlank
    private String status;

}
