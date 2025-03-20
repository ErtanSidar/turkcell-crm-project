package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {
    Optional<District> findByNameIgnoreCase(String name);

    List<District> findByCityId(UUID cityId);

    @Transactional
    @Modifying
    @Query("UPDATE District e SET e.deletedAt = :deletedAt, e.deletedBy = :deletedBy WHERE e.id = :id")
    void softDelete(@Param("id") UUID id, @Param("deletedAt") LocalDateTime deletedAt, @Param("deletedBy") String deletedBy);
}
