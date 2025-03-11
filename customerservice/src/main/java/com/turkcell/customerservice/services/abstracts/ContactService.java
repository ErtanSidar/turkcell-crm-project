package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.contactRequests.CreateContactRequest;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.UpdateContactRequest;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.*;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    CreatedContactResponse add(CreateContactRequest request);

    List<GetAllContactResponse> findAll();

    GetContactResponse findById(UUID id);

    GetContactByCustomerIdResponse getByCustomerId(UUID customerId);

    UpdatedContactResponse update(UpdateContactRequest request, UUID id);

    DeletedContactResponse delete(UUID id);
}
