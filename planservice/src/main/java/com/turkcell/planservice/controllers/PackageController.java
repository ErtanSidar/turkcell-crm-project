package com.turkcell.planservice.controllers;

import com.turkcell.planservice.dtos.packagedtos.requests.CreatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.requests.UpdatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.responses.PackageResponse;
import com.turkcell.planservice.services.abstracts.PackageService;
import com.turkcell.planservice.util.GenericResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/packages")
@CrossOrigin
@Log4j2
public class PackageController {


    private final PackageService packageService;


    public PackageController(PackageService packageService) {
        this.packageService = packageService;

    }


    @GetMapping
    public GetListResponse<PackageResponse> getAllPackages(@RequestParam int page, @RequestParam int size) {
        log.info("Received request to listing all packages");
        return packageService.gelAllPackages(new PageInfo(page, size));
    }


    @GetMapping("/getOnePackage")
    public PackageResponse getOnePackage(@RequestParam UUID id) {
        log.info("Received request to get one package with id {}", id);
        return packageService.getOnePackage(id);
    }



    @PostMapping
    public GenericResponse<String> createPackage(@RequestBody @Valid CreatePackageRequest createPackageRequest) {
        log.info("Received request to create package {}", createPackageRequest.getPackageName());
        packageService.createPackage(createPackageRequest);
        return GenericResponse.success("generic.package.created");
    }



    @PutMapping
    public GenericResponse<String> updatePackage(@RequestParam UUID id, @RequestBody @Valid UpdatePackageRequest updatePackageRequest) {
        log.info("Received request to update package with id {}", id);
        packageService.updatePackage(id, updatePackageRequest);
        return GenericResponse.success("generic.package.updated");
    }


    @DeleteMapping
    public GenericResponse<String> deletePackage(@RequestParam UUID packageId) {
        log.info("Received request to delete package with id {}", packageId);
        packageService.delete(packageId);
        return GenericResponse.success("generic.package.deleted");
    }

}
