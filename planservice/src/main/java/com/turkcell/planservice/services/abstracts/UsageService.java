package com.turkcell.planservice.services.abstracts;

import com.turkcell.planservice.dtos.usagedtos.requests.CreateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.requests.UpdateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Usage;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;

public interface UsageService {

    GetListResponse<UsageResponse> getAllUsages(PageInfo pageInfo);

    void delete(UUID id);

    UsageResponse getOneUsage(UUID id);

    void createUsage(CreateUsageRequest createUsageRequest);

    void updateUsage(UUID id, UpdateUsageRequest updateUsageRequest);
}
