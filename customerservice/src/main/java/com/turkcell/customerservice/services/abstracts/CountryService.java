package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.countryRequests.CreateCountryRequest;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.UpdateCountryRequest;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.CreatedCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.GetAllCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.GetCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.UpdatedCountryResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface CountryService {
    GetListResponse<GetAllCountryResponse> getAll(PageInfo pageInfo);

    GetCountryResponse getById(UUID id);

    CreatedCountryResponse add(CreateCountryRequest request);

    UpdatedCountryResponse update(UpdateCountryRequest request, UUID id);

    void delete(UUID id);
}
