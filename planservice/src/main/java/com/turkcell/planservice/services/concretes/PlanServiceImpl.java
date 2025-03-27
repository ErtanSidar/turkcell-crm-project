package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.plandtos.requests.CreatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.requests.UpdatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.entities.Plan;
import com.turkcell.planservice.mappers.PlanMapper;
import com.turkcell.planservice.repositories.PlanRepository;
import com.turkcell.planservice.rules.PlanBusinessRules;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import com.turkcell.planservice.services.abstracts.PlanService;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.UUID;

@Service

public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanBusinessRules planBusinessRules;
    private final SubscriptionBusinessRules subscriptionBusinessRules;

    public PlanServiceImpl(PlanRepository planRepository, PlanBusinessRules planBusinessRules, SubscriptionBusinessRules subscriptionBusinessRules) {
        this.planRepository = planRepository;
        this.planBusinessRules = planBusinessRules;
        this.subscriptionBusinessRules = subscriptionBusinessRules;
    }



    @Override
    public PlanResponse getOnePlan(UUID id) {

        Plan plan=planRepository.findById(id).orElseThrow(
                ()-> new BusinessException("Plan with id: " + id + " not found"));
        PlanResponse planResponse = PlanMapper.INSTANCE.createPlanResponseFromPlan(plan);
        return planResponse;
    }

    @Override
    public void createPlan(CreatePlanRequest createPlanRequest) {
        planBusinessRules.checkIfPlanNameExists(createPlanRequest.getPlanName());


        Plan plan = PlanMapper.INSTANCE.createPlanFromCreatePlanRequest(createPlanRequest);

        planRepository.save(plan);
    }

    @Override
    public void updatePlan(UUID id, UpdatePlanRequest updatePlanRequest) {
        planBusinessRules.checkIfPlanExists(id);


        Plan plan = planRepository.findById(id).orElseThrow(
                ()-> new BusinessException("Package with id: " + id + " not found"));


        PlanMapper.INSTANCE.updatePlanFromRequest(updatePlanRequest, plan);
        planRepository.save(plan);
    }


    @Override
    public GetListResponse<PlanResponse> getAllPlans(PageInfo pageInfo) {
        return ListResponse.get(pageInfo,
                planRepository,
                PlanMapper.INSTANCE::createPlanResponseFromPlan);
    }

    @Override
    public void deleById(UUID id) {
        planBusinessRules.checkIfPlanExists(id);
        subscriptionBusinessRules.checkIfPlanCanBeDeleted(id);

        planRepository.softDelete(id, LocalDateTime.now(),AuditServiceImpl.USER);
    }


}
