package com.turkcell.analyticservice.application.packages.mapper;


import com.turkcell.analyticservice.application.packages.command.create.CreatePackageCommand;
import com.turkcell.analyticservice.application.packages.command.create.CreatedPackageResponse;
import com.turkcell.analyticservice.application.packages.command.update.UpdatePackageCommand;
import com.turkcell.analyticservice.application.packages.command.update.UpdatedPackageResponse;
import com.turkcell.analyticservice.domain.entity.Package;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PackageMapper {
    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);

    Package createPackageFromCreateCommand(CreatePackageCommand createPackageCommand);
    CreatedPackageResponse createPackageResponseFromPackage(Package pack);

    @Mapping(target = "id", ignore = true)
    void updatePackageFromUpdateCommand(UpdatePackageCommand updatePackageCommand, @MappingTarget Package pack);
    UpdatedPackageResponse createUpdatedPackageResponse(Package pack);
}