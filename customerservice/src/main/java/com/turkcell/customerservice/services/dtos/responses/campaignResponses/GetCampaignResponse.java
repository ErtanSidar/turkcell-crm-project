package com.turkcell.customerservice.services.dtos.responses.campaignResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCampaignResponse {
    private String name;

    private double discountPercentage;

    private String validFrom;

    private String validUntil;
}
