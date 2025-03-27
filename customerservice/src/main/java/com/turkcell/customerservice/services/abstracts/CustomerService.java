package com.turkcell.customerservice.services.abstracts;

import com.essoft.dto.customer.GetCustomerResponse;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.CreatedCorporateCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetAllCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.UpdatedCustomerResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface CustomerService {

    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) throws Exception;

    CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest request);

    GetListResponse<GetAllCustomerResponse> getAll(PageInfo pageInfo);

    GetListResponse<? extends GetAllCustomerResponse> getAllWithCustomerType(PageInfo pageInfo);

    <T extends GetCustomerResponse> T findById(UUID id);

    UpdatedCustomerResponse update(UpdateCustomerRequest request, UUID id);

    void delete(UUID id);

    void addCustomerToCampaign(UUID customerId, UUID campaignId);
}
