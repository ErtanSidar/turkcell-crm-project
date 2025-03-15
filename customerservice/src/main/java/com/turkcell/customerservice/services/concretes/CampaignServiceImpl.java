package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.repositories.CampaignRepository;
import com.turkcell.customerservice.services.abstracts.CampaignService;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.CreateCampaignRequest;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.UpdateCampaignRequest;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.*;
import com.turkcell.customerservice.services.mappers.CampaignMapper;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    @Override
    public GetListResponse<GetAllCampaignResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, campaignRepository, CampaignMapper.INSTANCE::getAllCampaignResponseFromCampaign);
    }

    @Override
    public GetCampaignResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreatedCampaignResponse add(CreateCampaignRequest request) {
        return null;
    }

    @Override
    public UpdatedCampaignResponse update(UpdateCampaignRequest request, UUID id) {
        return null;
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        campaignRepository.softDelete(id, LocalDateTime.now(), AuditAwareImpl.USER);
    }
}
