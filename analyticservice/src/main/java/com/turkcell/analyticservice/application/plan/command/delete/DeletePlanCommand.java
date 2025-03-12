package com.turkcell.analyticservice.application.plan.command.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.persistence.plan.PlanRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeletePlanCommand implements Command<DeletedPlanResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class DeletePlanCommandHandler implements Command.Handler<DeletePlanCommand, DeletedPlanResponse> {
        private final PlanRepository planRepository;

        @Override
        public DeletedPlanResponse handle(DeletePlanCommand command) {
            Plan plan = planRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("Plan not found"));
            planRepository.deleteById(command.getId());
            return new DeletedPlanResponse(command.getId(), "Plan successfully deleted");
        }
    }
}