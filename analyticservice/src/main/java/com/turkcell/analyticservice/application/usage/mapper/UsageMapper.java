package com.turkcell.analyticservice.application.usage.mapper;

import com.turkcell.analyticservice.application.usage.command.create.CreateUsageCommand;
import com.turkcell.analyticservice.application.usage.command.create.CreatedUsageResponse;
import com.turkcell.analyticservice.application.usage.command.update.UpdateUsageCommand;
import com.turkcell.analyticservice.application.usage.command.update.UpdatedUsageResponse;
import com.turkcell.analyticservice.domain.entity.Usage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsageMapper {
    UsageMapper INSTANCE = Mappers.getMapper(UsageMapper.class);


    Usage createUsageFromCreateCommand(CreateUsageCommand command);
    CreatedUsageResponse createUsageResponseFromUsage(Usage usage);


    @Mapping(target = "id", ignore = true)
    void updateUsageFromUpdateCommand(UpdateUsageCommand command, @MappingTarget Usage usage);
    UpdatedUsageResponse createUpdatedUsageResponse(Usage usage);
}