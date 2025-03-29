package com.turkcell.planservice.mappers;

import com.turkcell.planservice.dtos.usagedtos.requests.CreateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.requests.UpdateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Usage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsageMapper {

    UsageMapper INSTANCE = Mappers.getMapper(UsageMapper.class);

    UsageResponse createUsageResponseFromUsage(Usage usage);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "customerId", source = "customerId")
    Usage createUsageFromCreateUsageRequest(CreateUsageRequest createUsageRequest);

    @Mapping(target = "id", ignore = true) // ID should not be changed
    @Mapping(target = "customerId", ignore = true) // Customer should remain unchanged
    @Mapping(target = "product", ignore = true)  // Product should remain unchanged
    void updateUsageFromUpdateUsageRequest(@MappingTarget Usage usage, UpdateUsageRequest request);
}
