package com.turkcell.customersupportservice.repositories;

import com.turkcell.customersupportservice.entities.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends MongoRepository<Ticket, UUID> {
    List<Ticket> findByCustomerId(UUID customerId);

    Optional<Ticket> findBySubjectIgnoreCase(String subject);



}
