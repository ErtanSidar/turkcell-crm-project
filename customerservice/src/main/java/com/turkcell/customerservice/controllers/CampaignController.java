package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.CampaignService;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.CreateCampaignRequest;
import com.turkcell.customerservice.services.dtos.requests.campaignRequests.UpdateCampaignRequest;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.CreatedCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.GetAllCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.GetCampaignResponse;
import com.turkcell.customerservice.services.dtos.responses.campaignResponses.UpdatedCampaignResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/campaigns")
@AllArgsConstructor
public class CampaignController {

    private CampaignService campaignService;

    @PostMapping
    public CreatedCampaignResponse add(@Valid @RequestBody CreateCampaignRequest request) {
        return campaignService.add(request);
    }

    @GetMapping()
    public GetListResponse<GetAllCampaignResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return campaignService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetCampaignResponse getById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return campaignService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCampaignResponse update(@Valid @RequestBody UpdateCampaignRequest request, @PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return campaignService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        campaignService.delete(id);
    }

}
