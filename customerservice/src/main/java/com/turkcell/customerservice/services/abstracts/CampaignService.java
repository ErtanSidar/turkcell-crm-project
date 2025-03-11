package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.campaignRequests.CreateCampaignRequest;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.UpdateCampaignRequest;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface CampaignService {

    GetListResponse<GetAllCampaignResponse> getAll(PageInfo pageInfo);

    GetCampignRespponse getById(UUID id);

    CreatedCampaignResponse add(CreateCampaignRequest request);

    UpdatedCampaignResponse update(UpdateCampaignRequest request, UUID id);

    DeletedCampaignResponse delete(UUID id);
}
