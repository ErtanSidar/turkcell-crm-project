package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {
    Optional<District> findByNameIgnoreCase(String name);
    @Query(value = "SELECT * FROM districts WHERE deleted_date IS null", nativeQuery = true)
    List<District> findAllIfDeletedDateIsNull();
    List<District> findByCityId(UUID cityId);
}
