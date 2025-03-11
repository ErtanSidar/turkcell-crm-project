package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, UUID> {
}
