package com.turkcell.analyticservice.application.plan.command.update;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.plan.mapper.PlanMapper;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.persistence.plan.PlanRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlanCommand implements Command<UpdatedPlanResponse> {
    private UUID id;
    private String planName;
    private String planType;
    private double monthlyFee;
    private int internetQuota;
    private int callMinutes;
    private int smsCount;
    private String description;

    @Component
    @RequiredArgsConstructor
    public static class UpdatePlanCommandHandler implements Command.Handler<UpdatePlanCommand, UpdatedPlanResponse> {
        private final PlanRepository planRepository;

        @Override
        public UpdatedPlanResponse handle(UpdatePlanCommand command) {
            Plan plan = planRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("Plan not found"));
            PlanMapper planMapper = PlanMapper.INSTANCE;
            planMapper.updatePlanFromUpdateCommand(command, plan);
            planRepository.save(plan);
            return planMapper.createUpdatedPlanResponse(plan);
        }
    }
}