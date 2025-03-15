package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Campaign;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.GetAllCampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    GetAllCampaignResponse getAllCampaignResponseFromCampaign(Campaign campaign);
}
