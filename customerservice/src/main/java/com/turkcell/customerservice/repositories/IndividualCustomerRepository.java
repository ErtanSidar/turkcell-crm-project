package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, UUID> {
}
