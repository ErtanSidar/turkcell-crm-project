package com.turkcell.analyticservice.persistence.usage;

import com.turkcell.analyticservice.domain.entity.Usage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UsageRepository extends MongoRepository<Usage, UUID> {
}
