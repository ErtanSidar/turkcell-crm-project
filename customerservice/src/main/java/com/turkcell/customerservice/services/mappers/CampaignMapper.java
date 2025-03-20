package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Campaign;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.CreateCampaignRequest;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.UpdateCampaignRequest;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.CreatedCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.GetAllCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.GetCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.UpdatedCampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    GetAllCampaignResponse getAllCampaignResponseFromCampaign(Campaign campaign);

    GetCampaignResponse getCampaignResponseFromCampaign(Campaign foundCampaign);

    Campaign campaignFromCreateCampaignRequest(CreateCampaignRequest request);

    CreatedCampaignResponse createdCampaignResponseFromCampaign(Campaign createdCampaign);

    Campaign campaignFromUpdateCampaignRequest(UpdateCampaignRequest request);

    UpdatedCampaignResponse updatedCampaignResponseFromCampaign(Campaign updatedCampaign);
}
