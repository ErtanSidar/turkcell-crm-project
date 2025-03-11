package com.turkcell.billingpaymentservice.cqrs.application.payment.command.delete;
import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.payment.PaymentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class DeletePaymentCommand implements Command<DeletedPaymentResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class DeletePaymentCommandHandler implements Command.Handler<DeletePaymentCommand, DeletedPaymentResponse> {

        private final PaymentRepository paymentRepository;

        @Override
        public DeletedPaymentResponse handle(DeletePaymentCommand deletePaymentCommand) {
            paymentRepository.deleteById(deletePaymentCommand.getId());
            return new DeletedPaymentResponse(deletePaymentCommand.getId());
        }
    }
}
