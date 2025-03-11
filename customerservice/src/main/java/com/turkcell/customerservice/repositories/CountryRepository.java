package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    Optional<Country> findByNameIgnoreCase(String name);

    @Query(value = "SELECT * FROM countries WHERE deleted_date IS null", nativeQuery = true)
    Page<Country> findAllIfDeletedDateIsNull(Pageable pageable);
}
