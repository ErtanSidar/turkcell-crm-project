package com.turkcell.planservice.repositories;


import com.turkcell.planservice.entities.Plan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {


    boolean existsByPlanName(String planName);
    Optional<Plan> findByPlanName(String planName);

    Optional<Plan> findByIdAndDeletedAtIsNull(UUID id);
    boolean existsByPlanNameAndDeletedAtIsNull(String planName);

    @Transactional
    @Modifying
    @Query("UPDATE Plan e SET e.deletedAt = :deletedAt, e.deletedBy = :deletedBy WHERE e.id = :id")
    void softDelete(@Param("id") UUID id, @Param("deletedAt") LocalDateTime deletedAt, @Param("deletedBy") String deletedBy);
}
