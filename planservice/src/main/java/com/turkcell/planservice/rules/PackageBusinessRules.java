package com.turkcell.planservice.rules;

import com.turkcell.planservice.repositories.PackageRepository;
import io.github.ertansidar.exception.type.BusinessException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PackageBusinessRules {

    private final PackageRepository packageRepository;

    public PackageBusinessRules(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public void checkIfPackageExists(UUID packageId) {
        if (packageRepository.findByIdAndDeletedAtIsNull(packageId).isEmpty()) {
            throw new BusinessException("Package not found or deleted");
        }
    }

    public void checkIfPackageNameExists(String packageName) {
        if (packageRepository.existsByPackageName(packageName)) {
            throw new RuntimeException("A package with this name already exists");
        }
    }


}
