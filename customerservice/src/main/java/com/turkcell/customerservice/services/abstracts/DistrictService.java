package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.turkcell.customerservice.services.dtos.responses.districtResponses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;

public interface DistrictService {
    GetListResponse<GetAllDistrictResponse> getAll(PageInfo pageInfo);

    GetDistrictResponse getById(UUID id);

    CreatedDistrictResponse add(CreateDistrictRequest request);

    UpdatedDistrictResponse update(UpdateDistrictRequest request, UUID id);

    void delete(UUID id);
}
