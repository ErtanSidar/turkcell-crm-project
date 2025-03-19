package com.turkcell.analyticservice.persistence.plan;

import com.turkcell.analyticservice.domain.entity.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.UUID;

public interface PlanRepository extends MongoRepository<Plan, UUID> {
}
