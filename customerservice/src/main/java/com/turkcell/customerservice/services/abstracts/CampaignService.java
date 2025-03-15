package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.campaignRequests.CreateCampaignRequest;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.UpdateCampaignRequest;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.CreatedCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.GetAllCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.GetCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.UpdatedCampaignResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface CampaignService {

    GetListResponse<GetAllCampaignResponse> getAll(PageInfo pageInfo);

    GetCampaignResponse getById(UUID id);

    CreatedCampaignResponse add(CreateCampaignRequest request);

    UpdatedCampaignResponse update(UpdateCampaignRequest request, UUID id);

    void delete(UUID id);
}
