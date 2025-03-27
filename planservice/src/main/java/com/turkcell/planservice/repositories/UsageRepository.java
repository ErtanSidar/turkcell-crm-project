package com.turkcell.planservice.repositories;

import com.turkcell.planservice.entities.Usage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsageRepository extends JpaRepository<Usage, UUID> {

//    List<Usage> findByCustomerId(UUID customerId);
//    boolean existsByProductId(UUID productId);
    Optional<Usage> findByIdAndDeletedAtIsNull(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Usage e SET e.deletedAt = :deletedAt, e.deletedBy = :deletedBy WHERE e.id = :id")
    void softDelete(@Param("id") UUID id, @Param("deletedAt") LocalDateTime deletedAt, @Param("deletedBy") String deletedBy);
}
