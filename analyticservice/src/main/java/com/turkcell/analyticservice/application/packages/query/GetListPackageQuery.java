package com.turkcell.analyticservice.application.packages.query;
import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Package;
import com.turkcell.analyticservice.persistence.packages.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

public class GetListPackageQuery implements Command<List<GetListPackageItemDto>> {

    @Component
    @RequiredArgsConstructor
    public static class GetListPackageQueryHandler
            implements Command.Handler<GetListPackageQuery, List<GetListPackageItemDto>> {

        private final PackageRepository packageRepository;

        @Override
        public List<GetListPackageItemDto> handle(GetListPackageQuery query) {
            List<Package> packages = packageRepository.findAll();

            return packages.stream()
                    .map(pkg -> new GetListPackageItemDto(pkg.getId(), pkg.getPackageName(),pkg.getPackageType()))
                    .toList();
        }
    }
}