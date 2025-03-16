package com.turkcell.planservice.repositories;

import com.turkcell.planservice.entities.Usage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsageRepository extends JpaRepository<Usage, UUID> {

//    List<Usage> findByCustomerId(UUID customerId);
//    boolean existsByProductId(UUID productId);
    Optional<Usage> findByIdAndDeletedAtIsNull(UUID id);
}
