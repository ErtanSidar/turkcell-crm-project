package com.turkcell.planservice.mappers;

import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Usage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsageMapper {

    UsageMapper INSTANCE = Mappers.getMapper(UsageMapper.class);

    UsageResponse createUsageResponseFromUsage(Usage usage);
}
