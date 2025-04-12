package com.turkcell.customersupportservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeletedTicketResponse {
    private UUID id;
    private String message;

}
