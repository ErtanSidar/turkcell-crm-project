package com.turkcell.analyticservice.application.usage.query.list;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Usage;
import com.turkcell.analyticservice.persistence.usage.UsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

public class GetListUsageQuery implements Command<List<GetListUsageItemDto>> {

    @Component
    @RequiredArgsConstructor
    public static class GetListUsageQueryHandler
            implements Command.Handler<GetListUsageQuery, List<GetListUsageItemDto>> {

        private final UsageRepository usageRepository;

        @Override
        public List<GetListUsageItemDto> handle(GetListUsageQuery query) {
            List<Usage> usages = usageRepository.findAll();

            return usages.stream()
                    .map(usg -> new GetListUsageItemDto(usg.getId(),usg.getCustomerId(),usg.getProduct(),usg.getInternetUsed(),usg.getCallMinutesUsed(),usg.getSmsUsed(),usg.getTvHoursWatched()))
                    .toList();
        }
    }
}