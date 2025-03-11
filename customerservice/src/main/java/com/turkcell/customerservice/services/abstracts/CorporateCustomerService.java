package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.*;

import java.util.List;
import java.util.UUID;

public interface CorporateCustomerService {

    CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest request) throws Exception;
    List<GetAllCorporateCustomerResponse> findAll();

    GetCorporateCustomerResponse findById(UUID id);

    UpdatedCorporateCustomerResponse update(UpdateCorporateCustomerRequest request, UUID id) throws Exception;

    DeletedCorporateCustomerResponse delete(UUID id);
}
