package com.turkcell.analyticservice.application.packages.query.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Package;
import com.turkcell.analyticservice.persistence.packages.PackageRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetPackageByIdQuery implements Command<GetPackageByIdItemDto> {
    private final UUID id; //

    @Component
    @RequiredArgsConstructor
    public static class GetPackageByIdQueryHandler
            implements Command.Handler<GetPackageByIdQuery, GetPackageByIdItemDto> {

        private final PackageRepository packageRepository;

        @Override
        public GetPackageByIdItemDto handle(GetPackageByIdQuery query) {
            Package pkg = packageRepository.findById(query.getId())
                    .orElseThrow(() -> new RuntimeException("Package not found with id: " + query.getId()));

            return new GetPackageByIdItemDto(pkg.getId(), pkg.getPackageName(),
                    pkg.getPackageType(), pkg.getQuota(), pkg.getPrice(), pkg.getValidityPeriod());
        }
    }
}