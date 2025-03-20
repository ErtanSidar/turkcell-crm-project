package com.turkcell.analyticservice.application.packages.command.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.persistence.packages.PackageRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeletePackageCommand implements Command<DeletedPackageResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class DeletePackageCommandHandler
            implements Command.Handler<DeletePackageCommand, DeletedPackageResponse> {

        private final PackageRepository packageRepository;

        @Override
        public DeletedPackageResponse handle(DeletePackageCommand deletePackageCommand) {
            boolean exists = packageRepository.existsById(deletePackageCommand.getId());
            if (!exists) {
                return new DeletedPackageResponse(false, "Package not found");
            }

            packageRepository.deleteById(deletePackageCommand.getId());
            return new DeletedPackageResponse(true, "Package deleted successfully");
        }
    }
}