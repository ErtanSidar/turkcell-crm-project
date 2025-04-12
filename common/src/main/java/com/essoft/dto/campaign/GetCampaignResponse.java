package com.essoft.dto.campaign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCampaignResponse {

    private String name;

    private double discountPercentage;

    private String validFrom;

    private String validUntil;
}
