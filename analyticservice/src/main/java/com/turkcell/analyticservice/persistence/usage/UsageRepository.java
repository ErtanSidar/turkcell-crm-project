package com.turkcell.analyticservice.persistence.usage;

import com.turkcell.analyticservice.domain.entity.Usage;

import java.util.UUID;

public class UsageRepository extends JpaRepository<Usage, UUID> {
}
