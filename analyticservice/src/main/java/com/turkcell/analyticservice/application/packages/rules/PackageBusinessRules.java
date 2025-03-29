package com.turkcell.analyticservice.application.packages.rules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.turkcell.analyticservice.domain.entity.Package;

@Component
@RequiredArgsConstructor
public class PackageBusinessRules {

    public void checkIfPackageIsValid(Package pkg) {
        if (pkg == null) {
            throw new IllegalArgumentException("Package cannot be null.");
        }

        if (pkg.getProduct() == null) {
            throw new IllegalArgumentException("Product must be defined.");
        }

        if (pkg.getPackageName() == null || pkg.getPackageName().isBlank()) {
            throw new IllegalArgumentException("Package name must be provided.");
        }

        if (pkg.getPackageType() == null || pkg.getPackageType().isBlank()) {
            throw new IllegalArgumentException("Package type must be provided.");
        }

        if (pkg.getQuota() < 0) {
            throw new IllegalArgumentException("Quota cannot be negative.");
        }

        if (pkg.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        if (pkg.getValidityPeriod() <= 0) {
            throw new IllegalArgumentException("Validity period must be greater than 0.");
        }
    }

    public void validateForCreate(Package pkg) {
        checkIfPackageIsValid(pkg);

    }

    public void validateForUpdate(Package pkg) {
        checkIfPackageIsValid(pkg);
    }
}