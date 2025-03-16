package com.turkcell.planservice.services.abstracts;

import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Usage;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;

public interface UsageService {

    GetListResponse<UsageResponse> getAllUsages(PageInfo pageInfo);

    void deleteById(UUID id);

    Usage getOneUsage(UUID id);
}
