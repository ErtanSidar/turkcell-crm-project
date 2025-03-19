package com.turkcell.customersupportservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreatedTicketResponse {
    private UUID id;
    private String subject;
    private String description;
    private String status;
    private UUID customerId;

}
