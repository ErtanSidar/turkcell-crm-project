package com.turkcell.analyticservice.application.packages.command.create;


import an.awesome.pipelinr.Command;

import com.turkcell.analyticservice.application.packages.mapper.PackageMapper;
import com.turkcell.analyticservice.domain.entity.Package;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.packages.PackageRepository;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePackageCommand implements Command<CreatedPackageResponse>
{
    private Product product;
    private String packageName;
    private String packageType;

    @Component
    @RequiredArgsConstructor
    public static class CreatePackageCommandHandler
            implements Command.Handler<CreatePackageCommand, CreatedPackageResponse>
    {
        private final PackageRepository packageRepository;
        @Override
        public CreatedPackageResponse handle(CreatePackageCommand createPackageCommand) {
            PackageMapper packageMapper = PackageMapper.INSTANCE;
            Package pack = packageMapper.createPackageFromCreateCommand(createPackageCommand);
            packageRepository.save(pack);

            return packageMapper.createPackageResponseFromPackage(pack);
        }
    }
}