package com.turkcell.customersupportservice.repositories;

import com.turkcell.customersupportservice.entities.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends MongoRepository<Ticket, UUID> {
    List<Ticket> findByCustomerId(UUID customerId);

    Optional<Ticket> findBySubjectIgnoreCase(String subject);

    @Transactional
    @Modifying
    @Query("{ 'id': ?0 }")
    void softDelete(UUID id, LocalDateTime deletedAt);

    @Transactional
    @Query("{ 'id': ?0 }")
    void updateStatus(UUID id, String status);

}
