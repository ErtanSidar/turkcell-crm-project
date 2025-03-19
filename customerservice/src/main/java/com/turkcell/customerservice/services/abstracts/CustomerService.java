package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.customerRequests.CreateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.CreatedCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetAllCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.UpdatedCustomerResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface CustomerService {

    CreatedCustomerResponse add(CreateCustomerRequest request);

    GetListResponse<GetAllCustomerResponse> getAll(PageInfo pageInfo);

    GetCustomerResponse findById(UUID id);

    UpdatedCustomerResponse update(UpdateCustomerRequest request, UUID id);

    void delete(UUID id);

}
