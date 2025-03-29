package com.turkcell.planservice.services.abstracts;

import com.turkcell.planservice.dtos.plandtos.requests.CreatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.requests.UpdatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.entities.Plan;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;

public interface PlanService {
    GetListResponse<PlanResponse> getAllPlans(PageInfo pageInfo);

    void delete(UUID id);

    PlanResponse getOnePlan(UUID id);

    void createPlan(CreatePlanRequest createPlanRequest);

    void updatePlan(UUID id, UpdatePlanRequest updatePlanRequest);
}
