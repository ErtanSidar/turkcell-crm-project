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
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
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
        log.info("Getting one plan with id {}", id);
        Plan plan=planRepository.findById(id).orElseThrow(
                ()-> new BusinessException("Plan with id: " + id + " not found"));
        PlanResponse planResponse = PlanMapper.INSTANCE.createPlanResponseFromPlan(plan);
        return planResponse;
    }

    @Override
    public void createPlan(CreatePlanRequest createPlanRequest) {
        planBusinessRules.checkIfPlanNameExists(createPlanRequest.getPlanName());

        log.info("Creating plan {}", createPlanRequest.getPlanName());
        Plan plan = PlanMapper.INSTANCE.createPlanFromCreatePlanRequest(createPlanRequest);

        planRepository.save(plan);
    }

    @Override
    public void updatePlan(UUID id, UpdatePlanRequest updatePlanRequest) {
        planBusinessRules.checkIfPlanExists(id);
        log.info("Updating plan {}", updatePlanRequest.getPlanName());

        Plan plan = planRepository.findById(id).orElseThrow(
                ()-> new BusinessException("Package with id: " + id + " not found"));
        log.info("Plan found: " + plan.getPlanName());

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
        log.info("Deleting plan {}", id);
        planRepository.deleteById(id);
    }


}
