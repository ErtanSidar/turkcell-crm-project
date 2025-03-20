package com.turkcell.analyticservice.application.plan.mapper;

import com.turkcell.analyticservice.application.plan.command.create.CreatePlanCommand;
import com.turkcell.analyticservice.application.plan.command.create.CreatedPlanResponse;
import com.turkcell.analyticservice.application.plan.command.update.UpdatePlanCommand;
import com.turkcell.analyticservice.application.plan.command.update.UpdatedPlanResponse;
import com.turkcell.analyticservice.domain.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanMapper {
    PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);


    Plan createPlanFromCreateCommand(CreatePlanCommand command);
    CreatedPlanResponse createPlanResponseFromPlan(Plan plan);

    @Mapping(target = "id", ignore = true)
    void updatePlanFromUpdateCommand(UpdatePlanCommand command, @MappingTarget Plan plan);
    UpdatedPlanResponse createUpdatedPlanResponse(Plan plan);
}