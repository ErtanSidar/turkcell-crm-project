package com.turkcell.contractservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponse {

    private UUID ticketId;
    private UUID customerId;
    private String subject;
    private String description;
    private String status;
}
