package com.turkcell.analyticservice.persistence.packages;

import com.turkcell.analyticservice.domain.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package, UUID> {
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
