package com.turkcell.billingpaymentservice.cqrs.application.billing.command.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.delete.DeletedPaymentResponse;
import com.turkcell.billingpaymentservice.cqrs.core.AuditAwareImpl;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
            billingRepository.softDelete(deleteBillingCommand.getId(), LocalDateTime.now(), AuditAwareImpl.USER);
            return new DeletedBillingResponse(deleteBillingCommand.getId());
        }
    }
}
