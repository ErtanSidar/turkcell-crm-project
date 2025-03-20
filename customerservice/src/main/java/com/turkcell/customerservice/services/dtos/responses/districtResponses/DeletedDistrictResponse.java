package com.turkcell.customerservice.services.dtos.responses.districtResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedDistrictResponse {
    private UUID id;
    private String name;
    private UUID cityId;
}
