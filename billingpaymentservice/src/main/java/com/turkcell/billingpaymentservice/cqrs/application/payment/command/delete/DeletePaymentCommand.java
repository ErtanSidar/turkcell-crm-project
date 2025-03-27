package com.turkcell.billingpaymentservice.cqrs.application.payment.command.delete;
import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.payment.PaymentRepository;
import io.github.ertansidar.audit.AuditAwareImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DeletePaymentCommand implements Command<DeletedPaymentResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class DeletePaymentCommandHandler implements Command.Handler<DeletePaymentCommand, DeletedPaymentResponse> {

        private final PaymentRepository paymentRepository;
        private final AuditAwareImpl auditAware;

        @Override
        public DeletedPaymentResponse handle(DeletePaymentCommand deletePaymentCommand) {
            paymentRepository.softDelete(deletePaymentCommand.getId(), LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
            return new DeletedPaymentResponse(deletePaymentCommand.getId());
        }
    }
}
