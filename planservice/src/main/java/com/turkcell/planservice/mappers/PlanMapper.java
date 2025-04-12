package com.turkcell.planservice.mappers;

import com.turkcell.planservice.dtos.plandtos.requests.CreatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.requests.UpdatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.entities.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanMapper {

    PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);


    PlanResponse createPlanResponseFromPlan(Plan plan);

    @Mapping(target = "id", ignore = true)
    Plan createPlanFromCreatePlanRequest(CreatePlanRequest request);

    Plan createPlanFromPlanResponse(PlanResponse planResponse);

    void updatePlanFromRequest(UpdatePlanRequest request, @MappingTarget Plan plan);
}
