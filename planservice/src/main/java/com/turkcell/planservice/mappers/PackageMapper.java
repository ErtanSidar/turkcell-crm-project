package com.turkcell.planservice.mappers;

import com.turkcell.planservice.dtos.packagedtos.requests.CreatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.requests.UpdatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.responses.PackageResponse;
import com.turkcell.planservice.entities.Package;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PackageMapper {

    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);

    PackageResponse createPackageResponseFromPackage(Package pack);

    Package CreatePackageFromCreatePackageRequest(CreatePackageRequest request);

    void updatePackageFromRequest(UpdatePackageRequest request, @MappingTarget Package pack);


}
