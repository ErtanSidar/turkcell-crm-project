package com.turkcell.customersupportservice.services.concretes;

import com.essoft.dto.customer.GetCorporateCustomerResponse;
import com.essoft.dto.customer.GetCustomerResponse;
import com.essoft.dto.customer.GetIndividualCustomerResponse;
import com.essoft.event.ticket.TicketCreatedEvent;
import com.turkcell.customersupportservice.client.CustomerClient;
import com.turkcell.customersupportservice.coreBusiness.producer.TicketCreatedProducer;
import com.turkcell.customersupportservice.entities.Ticket;
import com.turkcell.customersupportservice.repositories.TicketRepository;
import com.turkcell.customersupportservice.services.abstracts.TicketService;
import com.turkcell.customersupportservice.services.dtos.requests.CreateTicketRequest;
import com.turkcell.customersupportservice.services.dtos.requests.UpdateTicketRequest;
import com.turkcell.customersupportservice.services.dtos.responses.*;
import com.turkcell.customersupportservice.services.mappers.TicketMapper;
import com.turkcell.customersupportservice.services.rules.TicketBusinessRules;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketBusinessRules ticketBusinessRules;
    private final TicketCreatedProducer ticketCreatedProducer;
    private final CustomerClient customerClient;

    @Override
    public GetListResponse<GetAllTicketResponse> getAll(PageInfo pageInfo) {
        return TicketServiceImpl.get(pageInfo, ticketRepository, TicketMapper.INSTANCE::getAllTicketResponseFromTicket);
    }

    public static <T, R, ID extends Serializable> GetListResponse<R> get(
            PageInfo pageInfo,
            MongoRepository<T, ID> repository,
            Function<T, R> mapper) {

        GetListResponse<R> response = new GetListResponse<>();
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<T> entities = repository.findAll(pageable);

        response.setItems(entities.stream().map(mapper).collect(Collectors.toList()));
        response.setTotalElements(entities.getTotalElements());
        response.setTotalPage(entities.getTotalPages());
        response.setSize(entities.getSize());
        response.setHasNext(entities.hasNext());
        response.setHasPrevious(entities.hasPrevious());

        return response;
    }


    @Override
    public GetTicketResponse getById(UUID id) {
        ticketBusinessRules.checkTicketIdExists(id);
        ticketBusinessRules.checkTicketIsDeleted(id);

        Ticket foundTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Ticket not found"));
        return TicketMapper.INSTANCE.getTicketResponseFromTicket(foundTicket);
    }

    @Override
    public CreatedTicketResponse add(CreateTicketRequest request) {
        // ticketBusinessRules.checkTicketSubjectExists(request.getSubject());
        // ticketBusinessRules.checkTicketCustomerExists(request.getCustomerId());
        Ticket ticket = TicketMapper.INSTANCE.ticketFromCreateTicketRequest(request);
        ticket.setId(UUID.randomUUID());
        ticket.setCreatedAt(LocalDateTime.now());
        Ticket createdTicket = ticketRepository.save(ticket);

        TicketCreatedEvent ticketCreatedEvent = new TicketCreatedEvent();

        GetCustomerResponse customer = customerClient.findById(createdTicket.getCustomerId());

        if (customer.getCustomerType().equals("INDIVIDUAL")) {
            GetIndividualCustomerResponse individualCustomer = (GetIndividualCustomerResponse) customer;
            ticketCreatedEvent.setCustomerName(individualCustomer.getFirstName());
        } else {
            GetCorporateCustomerResponse corporateCustomer = (GetCorporateCustomerResponse) customer;
            ticketCreatedEvent.setCustomerName(corporateCustomer.getCompanyName());
        }
        ticketCreatedEvent.setEmail(customer.getContacts().get(0).getEmail());
        ticketCreatedEvent.setSubject(createdTicket.getSubject());
        ticketCreatedEvent.setDescription(createdTicket.getDescription());
        ticketCreatedEvent.setStatus(createdTicket.getStatus());

        ticketCreatedProducer.sendMessage(ticketCreatedEvent);

        return TicketMapper.INSTANCE.createdTicketResponseFromTicket(createdTicket);
    }

    @Override
    public UpdatedTicketResponse update(UpdateTicketRequest request, UUID id) {
        ticketBusinessRules.checkIfTicketAlreadyClosed(id);
        ticketBusinessRules.checkTicketIdExists(id);
        ticketBusinessRules.checkTicketStatusIsValid(request.getStatus());
        Ticket foundTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Ticket not found"));

        Ticket ticket = TicketMapper.INSTANCE.ticketFromUpdateTicketRequest(request);
        ticket.setId(foundTicket.getId());
        ticket.setUpdatedAt(LocalDateTime.now());
        Ticket updatedTicket = ticketRepository.save(ticket);

        return TicketMapper.INSTANCE.updateTicketResponseFromTicket(updatedTicket);
    }


    @Override
    public DeletedTicketResponse delete(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Ticket not found"));

        ticketRepository.delete(ticket);

        return TicketMapper.INSTANCE.deletedTicketResponseFromTicket(ticket);
    }

}

