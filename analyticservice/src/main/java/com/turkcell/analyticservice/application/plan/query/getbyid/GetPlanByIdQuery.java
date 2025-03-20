package com.turkcell.analyticservice.application.plan.query.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.persistence.plan.PlanRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetPlanByIdQuery implements Command<GetPlanByIdItemDto> {
    private final UUID id;

    @Component
    @RequiredArgsConstructor
    public static class GetPlanByIdQueryHandler
            implements Command.Handler<GetPlanByIdQuery, GetPlanByIdItemDto> {

        private final PlanRepository planRepository;

        @Override
        public GetPlanByIdItemDto handle(GetPlanByIdQuery query) {
            Plan plan = planRepository.findById(query.getId())
                    .orElseThrow(() -> new RuntimeException("Plan not found with id: " + query.getId()));

            return new GetPlanByIdItemDto(plan.getId(), plan.getPlanName(), plan.getPlanType(),
                    plan.getMonthlyFee(), plan.getInternetQuota(), plan.getCallMinutes(),
                    plan.getSmsCount(), plan.getDescription());
        }
    }
}
