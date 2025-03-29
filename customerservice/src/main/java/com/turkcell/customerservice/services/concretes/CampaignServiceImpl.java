package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Campaign;
import com.turkcell.customerservice.entities.City;
import com.turkcell.customerservice.repositories.CampaignRepository;
import com.turkcell.customerservice.services.abstracts.CampaignService;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.CreateCampaignRequest;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.UpdateCampaignRequest;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.*;
import com.turkcell.customerservice.services.mappers.CampaignMapper;
import com.turkcell.customerservice.services.mappers.CityMapper;
import com.turkcell.customerservice.services.rules.CampaignBusinessRules;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
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
    private final CampaignBusinessRules campaignBusinessRules;
    private final AuditAwareImpl auditAware;

    @Override
    public GetListResponse<GetAllCampaignResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, campaignRepository, CampaignMapper.INSTANCE::getAllCampaignResponseFromCampaign);
    }

    @Override
    public GetCampaignResponse getById(UUID id) {
        campaignBusinessRules.checkCampaignIdExists(id);
        Campaign foundCampaign = campaignRepository.findById(id).get();
        return CampaignMapper.INSTANCE.getCampaignResponseFromCampaign(foundCampaign);
    }

    @Override
    public CreatedCampaignResponse add(CreateCampaignRequest request) {
        Campaign campaign = CampaignMapper.INSTANCE.campaignFromCreateCampaignRequest(request);
        Campaign createdCampaign = campaignRepository.save(campaign);
        return CampaignMapper.INSTANCE.createdCampaignResponseFromCampaign(createdCampaign);
    }

    @Override
    public UpdatedCampaignResponse update(UpdateCampaignRequest request, UUID id) {
        campaignBusinessRules.checkCampaignIdExists(id);
        Campaign campaign = CampaignMapper.INSTANCE.campaignFromUpdateCampaignRequest(request);
        Campaign updatedCampaign = campaignRepository.save(campaign);
        return CampaignMapper.INSTANCE.updatedCampaignResponseFromCampaign(updatedCampaign);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        campaignBusinessRules.checkCampaignIdExists(id);
        campaignRepository.softDelete(id, LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
    }
}
