package com.turkcell.analyticservice.application.plan.command.create;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.plan.mapper.PlanMapper;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.plan.PlanRepository;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlanCommand implements Command<CreatedPlanResponse> {
    private Product product;
    private String planName;
    private String planType;
    private double monthlyFee;
    private int internetQuota;
    private int callMinutes;
    private int smsCount;
    private String description;

    @Component
    @RequiredArgsConstructor
    public static class CreatePlanCommandHandler implements Command.Handler<CreatePlanCommand, CreatedPlanResponse> {
        private final PlanRepository planRepository;

        @Override
        public CreatedPlanResponse handle(CreatePlanCommand command) {
            PlanMapper planMapper = PlanMapper.INSTANCE;
            Plan plan = planMapper.createPlanFromCreateCommand(command);
            planRepository.save(plan);
            return planMapper.createPlanResponseFromPlan(plan);
        }
    }
}