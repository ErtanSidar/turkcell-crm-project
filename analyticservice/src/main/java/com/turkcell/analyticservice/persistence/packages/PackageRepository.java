package com.turkcell.analyticservice.persistence.packages;

import com.turkcell.analyticservice.domain.entity.Package;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PackageRepository extends MongoRepository<Package,UUID> {
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
