package com.turkcell.customerservice.services.abstracts;

import com.essoft.dto.contact.GetContactResponse;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.CreateContactRequest;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.UpdateContactRequest;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.CreatedContactResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.GetAllContactResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.UpdatedContactResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface ContactService {
    CreatedContactResponse add(CreateContactRequest request);

    GetListResponse<GetAllContactResponse> getAll(PageInfo pageInfo);

    GetContactResponse findById(UUID id);

    UpdatedContactResponse update(UpdateContactRequest request, UUID id);

    void delete(UUID id);
}
