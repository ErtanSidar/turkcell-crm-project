package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.cityRequests.CreateCityRequest;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.UpdateCityRequest;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;

public interface CityService {
    GetListResponse<GetAllCityResponse> getAll(PageInfo pageInfo);

    GetCityResponse getById(UUID id);

    CreatedCityResponse add(CreateCityRequest request);

    UpdatedCityResponse update(UpdateCityRequest request, UUID id);

    void delete(UUID id);
}
