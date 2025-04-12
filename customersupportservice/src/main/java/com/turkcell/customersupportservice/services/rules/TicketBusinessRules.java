package com.turkcell.customersupportservice.services.rules;

import com.turkcell.customersupportservice.entities.Ticket;
import com.turkcell.customersupportservice.repositories.TicketRepository;
import com.turkcell.customersupportservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketBusinessRules {
    private final MessageService messageService;
    private final TicketRepository ticketRepository;

    public void checkTicketSubjectExists(String subject) {
        Optional<Ticket> ticket = ticketRepository.findBySubjectIgnoreCase(subject);
        if (ticket.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.TICKET_SUBJECT_EXISTS));
        }
    }

    public void checkTicketIdExists(UUID id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.TICKET_NOT_FOUND));
        }
    }

    public void checkTicketIsDeleted(UUID id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent() && ticket.get().getDeletedAt() != null) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.TICKET_IS_DELETED));
        }
    }

    public void checkTicketCustomerExists(UUID customerId) {
        List<Ticket> ticket = ticketRepository.findByCustomerId(customerId);
        if (ticket.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.TICKET_CUSTOMER_NOT_FOUND));
        }
    }

    public void checkTicketStatusIsValid(String status) {
        if (!status.equals("OPEN") && !status.equals("CLOSED")) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.TICKET_STATUS_INVALID));
        }
    }

    public void checkIfTicketAlreadyClosed(UUID id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent() && "CLOSED".equals(ticket.get().getStatus())) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.TICKET_ALREADY_CLOSED));
        }
    }
}
