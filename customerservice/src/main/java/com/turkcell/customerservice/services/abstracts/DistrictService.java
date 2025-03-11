package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.turkcell.customerservice.services.dtos.responses.districtResponses.*;

import java.util.List;
import java.util.UUID;

public interface DistrictService {
    List<GetAllDistrictResponse> getAll();

    GetDistrictResponse getById(UUID id);

    CreatedDistrictResponse add(CreateDistrictRequest request);

    UpdatedDistrictResponse update(UpdateDistrictRequest request, UUID id);

    DeletedDistrictResponse delete(UUID id);

    List<GetDistrictByCityIdResponse> getByCityId(UUID cityId);
}
