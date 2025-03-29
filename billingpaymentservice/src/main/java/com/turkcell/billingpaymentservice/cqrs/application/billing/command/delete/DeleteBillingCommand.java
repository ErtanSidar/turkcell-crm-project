package com.turkcell.billingpaymentservice.cqrs.application.billing.command.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import io.github.ertansidar.audit.AuditAwareImpl;
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
        private final AuditAwareImpl auditAware;

        @Override
        public DeletedBillingResponse handle(DeleteBillingCommand deleteBillingCommand) {
            billingRepository.softDelete(deleteBillingCommand.getId(), LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
            return new DeletedBillingResponse(deleteBillingCommand.getId());
        }
    }
}
