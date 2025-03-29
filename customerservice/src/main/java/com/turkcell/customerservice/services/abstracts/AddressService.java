package com.turkcell.customerservice.services.abstracts;


import com.essoft.dto.address.GetAddressResponse;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    GetListResponse<GetAllAddressResponse> getAll(PageInfo pageInfo);

    GetAddressResponse getById(UUID id);

    CreatedAddressResponse add(CreateAddressRequest request);

    UpdatedAddressResponse update(UpdateAddressRequest request, UUID id);

    void delete(UUID id);
}
