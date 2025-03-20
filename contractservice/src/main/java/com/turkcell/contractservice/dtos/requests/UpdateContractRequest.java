package com.turkcell.contractservice.dtos.requests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContractRequest {

    @Size(min = 3, max = 30, message = "Subject must be between 3 and 30 characters")
    private String subject;

    @Size(min = 3, max = 30, message = "Description must be between 3 and 30 characters")
    private String description;

    @Size(min = 3, max = 30, message = "Status must be between 3 and 30 characters")
    private String status;
}
