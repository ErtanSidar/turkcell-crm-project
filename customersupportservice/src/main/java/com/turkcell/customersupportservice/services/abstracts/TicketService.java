package com.turkcell.customersupportservice.services.abstracts;

import com.turkcell.customersupportservice.services.dtos.requests.CreateTicketRequest;
import com.turkcell.customersupportservice.services.dtos.requests.UpdateTicketRequest;
import com.turkcell.customersupportservice.services.dtos.responses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface TicketService {
    GetListResponse<GetAllTicketResponse> getAll(PageInfo pageInfo);

    GetTicketResponse getById(UUID id);

    CreatedTicketResponse add(CreateTicketRequest request);

    UpdatedTicketResponse update(UpdateTicketRequest request, UUID id);

    DeletedTicketResponse delete(UUID id);

}
