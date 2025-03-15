package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.CreatedCorporateCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.GetAllCorporateCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.GetCorporateCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.UpdatedCorporateCustomerResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface CorporateCustomerService {

    CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest request) throws Exception;

    GetListResponse<GetAllCorporateCustomerResponse> getAll(PageInfo pageInfo);

    GetCorporateCustomerResponse findById(UUID id);

    UpdatedCorporateCustomerResponse update(UpdateCorporateCustomerRequest request, UUID id) throws Exception;

    void delete(UUID id);
}
