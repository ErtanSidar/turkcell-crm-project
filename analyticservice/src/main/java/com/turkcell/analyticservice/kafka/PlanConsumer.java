package com.turkcell.analyticservice.kafka;

import com.essoft.event.plan.PlanCreatedEvent;
import com.turkcell.analyticservice.domain.entity.Plan;
import com.turkcell.analyticservice.persistence.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class PlanConsumer {

    private final PlanRepository planRepository;

    @KafkaListener(topics = "plan-created",groupId = "create-plan")
    private void consumePlan(PlanCreatedEvent event) {
        Plan plan = new Plan();
        plan.setPlanName(event.getPlanName());
        plan.setPlanType(event.getPlanType());
        plan.setMonthlyFee(event.getMonthlyFee());
        plan.setInternetQuota(event.getInternetQuota());
        plan.setCallMinutes(event.getCallMinutes());
        plan.setSmsCount(event.getSmsCount());
        plan.setDescription(event.getDescription());
        planRepository.save(plan);
    }

}
