package com.turkcell.analyticservice.application.usage.command.create;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.usage.mapper.UsageMapper;
import com.turkcell.analyticservice.application.usage.rules.UsageBusinessRules;
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
public class CreateUsageCommand implements Command<CreatedUsageResponse> {
    private UUID customerId;
    private Product product;
    private int internetUsed;
    private int callMinutesUsed;
    private int smsUsed;
    private int tvHoursWatched;

    @Component
    @RequiredArgsConstructor
    public static class CreateUsageCommandHandler implements Command.Handler<CreateUsageCommand, CreatedUsageResponse> {
        private final UsageRepository usageRepository;
        private final UsageBusinessRules usageBusinessRules;

        @Override
        public CreatedUsageResponse handle(CreateUsageCommand command) {
            UsageMapper usageMapper = UsageMapper.INSTANCE;
            Usage usage = usageMapper.createUsageFromCreateCommand(command);
            usageBusinessRules.validateForCreate(usage);
            usageRepository.save(usage);
            return usageMapper.createUsageResponseFromUsage(usage);
        }
    }
}
