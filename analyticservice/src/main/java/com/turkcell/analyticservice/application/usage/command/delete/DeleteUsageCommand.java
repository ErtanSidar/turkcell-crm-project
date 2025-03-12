package com.turkcell.analyticservice.application.usage.command.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Usage;
import com.turkcell.analyticservice.persistence.usage.UsageRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUsageCommand implements Command<DeletedUsageResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class DeleteUsageCommandHandler implements Command.Handler<DeleteUsageCommand, DeletedUsageResponse> {
        private final UsageRepository usageRepository;

        @Override
        public DeletedUsageResponse handle(DeleteUsageCommand command) {
            Usage usage = usageRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("Usage record not found"));
            usageRepository.deleteById(command.getId());
            return new DeletedUsageResponse(command.getId(), "Usage record successfully deleted");
        }
    }
}
