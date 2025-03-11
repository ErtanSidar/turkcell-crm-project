package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    Contact findByCustomerId(UUID customerId);
}
