package com.turkcell.planservice.services.abstracts;

import com.turkcell.planservice.dtos.packagedtos.requests.CreatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.requests.UpdatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.responses.PackageResponse;
import com.turkcell.planservice.entities.Package;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;

/**
 * Service implementation for managing packages.
 */
public interface PackageService {

    GetListResponse<PackageResponse> gelAllPackages(PageInfo pageInfo);

    void createPackage(CreatePackageRequest createPackageRequest);

    void deleteById(UUID packageId);

    /**
     * Fetches details of a single package by its ID.
     *
     * @param id The ID of the product to fetch.
     */

    PackageResponse getOnePackage(UUID id);

    void updatePackage(UUID id, UpdatePackageRequest updatePackageRequest);
}
