package com.turkcell.billingpaymentservice.cqrs.application.billing.command.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class DeleteBillingCommand implements Command<DeletedBillingResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class DeleteBillingCommandHandler implements Command.Handler<DeleteBillingCommand, DeletedBillingResponse> {

        private final BillingRepository billingRepository;

        @Override
        public DeletedBillingResponse handle(DeleteBillingCommand deleteBillingCommand) {
            billingRepository.deleteById(deleteBillingCommand.getId());
            return new DeletedBillingResponse(deleteBillingCommand.getId());
        }
    }
}
