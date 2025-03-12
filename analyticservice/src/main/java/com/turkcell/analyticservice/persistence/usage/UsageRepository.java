package com.turkcell.analyticservice.persistence.usage;

import com.turkcell.analyticservice.domain.entity.Usage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsageRepository extends JpaRepository<Usage, UUID> {
}
