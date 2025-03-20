package com.turkcell.analyticservice.application.usage.query.getbyid;


import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Usage;
import com.turkcell.analyticservice.persistence.usage.UsageRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetUsageByIdQuery implements Command<GetUsageByIdItemDto> {
    private final UUID id;

    @Component
    @RequiredArgsConstructor
    public static class GetUsageByIdQueryHandler
            implements Command.Handler<GetUsageByIdQuery, GetUsageByIdItemDto> {

        private final UsageRepository usageRepository;

        @Override
        public GetUsageByIdItemDto handle(GetUsageByIdQuery query) {
            Usage usage = usageRepository.findById(query.getId())
                    .orElseThrow(() -> new RuntimeException("Usage not found with id: " + query.getId()));

            return new GetUsageByIdItemDto(usage.getId(), usage.getCustomerId(),
                    usage.getProduct(), usage.getInternetUsed(),
                    usage.getCallMinutesUsed(), usage.getSmsUsed(),
                    usage.getTvHoursWatched());
        }
    }
}