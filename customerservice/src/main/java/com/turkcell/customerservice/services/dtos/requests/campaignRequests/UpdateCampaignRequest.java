package com.turkcell.customerservice.services.dtos.requests.campaignRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCampaignRequest {

    private String name;

    private double discountPercentage;

    private String validFrom;

    private String validUntil;
}
