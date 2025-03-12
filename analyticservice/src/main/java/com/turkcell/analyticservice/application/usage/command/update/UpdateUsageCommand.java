package com.turkcell.analyticservice.application.usage.command.update;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.usage.mapper.UsageMapper;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.domain.entity.Usage;
import com.turkcell.analyticservice.persistence.usage.UsageRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUsageCommand implements Command<UpdatedUsageResponse> {
    private UUID id;
    private UUID customerId;
    private Product product;
    private int internetUsed;
    private int callMinutesUsed;
    private int smsUsed;
    private int tvHoursWatched;

    @Component
    @RequiredArgsConstructor
    public static class UpdateUsageCommandHandler implements Command.Handler<UpdateUsageCommand, UpdatedUsageResponse> {
        private final UsageRepository usageRepository;

        @Override
        public UpdatedUsageResponse handle(UpdateUsageCommand command) {
            Usage usage = usageRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("Usage record not found"));
            UsageMapper usageMapper = UsageMapper.INSTANCE;
            usageMapper.updateUsageFromUpdateCommand(command, usage);
            usageRepository.save(usage);
            return usageMapper.createUpdatedUsageResponse(usage);
        }
    }
}
