package com.turkcell.customersupportservice.services.mappers;

import com.turkcell.customersupportservice.entities.Ticket;
import com.turkcell.customersupportservice.services.dtos.requests.CreateTicketRequest;
import com.turkcell.customersupportservice.services.dtos.requests.UpdateTicketRequest;
import com.turkcell.customersupportservice.services.dtos.responses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    GetAllTicketResponse getAllTicketResponseFromTicket(Ticket ticket);

    GetTicketResponse getTicketResponseFromTicket(Ticket ticket);

    Ticket ticketFromCreateTicketRequest(CreateTicketRequest createTicketRequest);

    CreatedTicketResponse createdTicketResponseFromTicket(Ticket ticket);

    Ticket ticketFromUpdateTicketRequest(UpdateTicketRequest updateTicketRequest);

    UpdatedTicketResponse updateTicketResponseFromTicket(Ticket ticket);

    DeletedTicketResponse deletedTicketResponseFromTicket(Ticket ticket);



}
