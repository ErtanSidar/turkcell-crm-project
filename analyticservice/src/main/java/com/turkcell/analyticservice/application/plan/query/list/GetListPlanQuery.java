package com.turkcell.analyticservice.application.plan.query.list;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.persistence.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

public class GetListPlanQuery implements Command<List<GetListPlanItemDto>> {

    @Component
    @RequiredArgsConstructor
    public static class GetListPlanQueryHandler
            implements Command.Handler<GetListPlanQuery, List<GetListPlanItemDto>> {

        private final PlanRepository planRepository;

        @Override
        public List<GetListPlanItemDto> handle(GetListPlanQuery query) {
            List<Plan> plans = planRepository.findAll();

            return plans.stream()
                    .map(pln -> new GetListPlanItemDto(pln.getId(),pln.getPlanName(),pln.getPlanType(), pln.getMonthlyFee(), pln.getInternetQuota(), pln.getCallMinutes(), pln.getSmsCount(), pln.getDescription()))
                    .toList();
        }
    }
}