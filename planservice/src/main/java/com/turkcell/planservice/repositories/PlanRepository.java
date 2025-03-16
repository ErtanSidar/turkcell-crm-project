package com.turkcell.planservice.repositories;


import com.turkcell.planservice.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {


    boolean existsByPlanName(String planName);
    Optional<Plan> findByPlanName(String planName);

    Optional<Plan> findByIdAndDeletedAtIsNull(UUID id);
    boolean existsByPlanNameAndDeletedAtIsNull(String planName);
}
