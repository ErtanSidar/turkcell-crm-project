package com.turkcell.planservice.repositories;

import com.turkcell.planservice.dtos.packagedtos.responses.PackageResponse;
import com.turkcell.planservice.entities.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package, UUID> {


    boolean existsByPackageName(String packageName);
    Optional<Package> findByPackageName(String packageName);

    Optional<Package> findByIdAndDeletedAtIsNull(UUID id);
    boolean existsByPackageNameAndDeletedAtIsNull(String packageName);
}
