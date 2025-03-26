package com.turkcell.analyticservice.application.packages.command.update;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.packages.rules.PackageBusinessRules;
import com.turkcell.analyticservice.persistence.packages.PackageRepository;
import com.turkcell.analyticservice.domain.entity.Package;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePackageCommand implements Command<UpdatedPackageResponse> {
    private UUID id;
    private String newPackageName;
    private String newPackageType;

    @Component
    @RequiredArgsConstructor
    public static class UpdatePackageCommandHandler
            implements Command.Handler<UpdatePackageCommand, UpdatedPackageResponse> {

        private final PackageRepository packageRepository;
        private final PackageBusinessRules packageBusinessRules;

        @Override
        public UpdatedPackageResponse handle(UpdatePackageCommand updatePackageCommand) {
            Package existingPackage = packageRepository.findById(updatePackageCommand.getId()).orElse(null);

            if (existingPackage == null) {
                return new UpdatedPackageResponse(false, "Package not found");
            }


            existingPackage.setPackageName(updatePackageCommand.getNewPackageName());
            existingPackage.setPackageType(updatePackageCommand.getNewPackageType());

            packageBusinessRules.validateForUpdate(existingPackage);

            packageRepository.save(existingPackage);

            return new UpdatedPackageResponse(true, "Package updated successfully");
        }
    }
}