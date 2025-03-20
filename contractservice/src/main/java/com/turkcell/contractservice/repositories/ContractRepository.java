package com.turkcell.contractservice.repositories;

import com.turkcell.contractservice.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {
}
