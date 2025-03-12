package com.turkcell.analyticservice.application.subscription.command.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Subscription;
import com.turkcell.analyticservice.persistence.subscription.SubscriptionRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteSubscriptionCommand implements Command<DeletedSubscriptionResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class DeleteSubscriptionCommandHandler implements Command.Handler<DeleteSubscriptionCommand, DeletedSubscriptionResponse> {
        private final SubscriptionRepository subscriptionRepository;

        @Override
        public DeletedSubscriptionResponse handle(DeleteSubscriptionCommand command) {
            Subscription subscription = subscriptionRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("Subscription not found"));
            subscriptionRepository.deleteById(command.getId());
            return new DeletedSubscriptionResponse(command.getId(), "Subscription successfully deleted");
        }
    }
}
