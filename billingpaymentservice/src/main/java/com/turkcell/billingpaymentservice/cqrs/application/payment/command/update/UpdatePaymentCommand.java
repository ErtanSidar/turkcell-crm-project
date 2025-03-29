package com.turkcell.billingpaymentservice.cqrs.application.payment.command.update;
import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.application.payment.mapper.PaymentMapper;
import com.turkcell.billingpaymentservice.cqrs.application.payment.rules.PaymentBusinessRules;
import com.turkcell.billingpaymentservice.cqrs.persistance.payment.PaymentRepository;
import com.turkcell.billingpaymentservice.cqrs.entities.Payment;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class UpdatePaymentCommand implements Command<UpdatedPaymentResponse> {
    private UUID id;
    private Double newAmount; // Güncellenecek ödeme miktarı
    private String newPaymentMethod; // Güncellenecek ödeme yöntemi

    @Component
    @RequiredArgsConstructor
    public static class UpdatePaymentCommandHandler implements Command.Handler<UpdatePaymentCommand, UpdatedPaymentResponse> {

        private final PaymentRepository paymentRepository;
        private final PaymentBusinessRules paymentBusinessRules;

        @Override
        public UpdatedPaymentResponse handle(UpdatePaymentCommand updatePaymentCommand) {

            paymentBusinessRules.checkPaymentIdExists(updatePaymentCommand.getId());
            PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
            Payment payment = paymentMapper.paymentFromUpdatePaymentCommand(updatePaymentCommand);
            payment.setId(updatePaymentCommand.getId());
            paymentRepository.save(payment);
            return paymentMapper.updatedPaymentResponseFromPayment(payment);
        }
    }
}
