package com.turkcell.analyticservice.persistence.plan;

import com.turkcell.analyticservice.domain.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {
}
