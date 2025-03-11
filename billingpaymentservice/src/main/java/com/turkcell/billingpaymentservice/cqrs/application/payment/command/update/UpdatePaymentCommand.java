package com.turkcell.billingpaymentservice.cqrs.application.payment.command.update;
import an.awesome.pipelinr.Command;
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

        @Override
        public UpdatedPaymentResponse handle(UpdatePaymentCommand updatePaymentCommand) {
            Payment payment = paymentRepository.findById(updatePaymentCommand.getId())
                    .orElseThrow(() -> new RuntimeException("Payment not found"));

            // Yeni değerleri atama
            payment.setAmount(updatePaymentCommand.getNewAmount());
            payment.setPaymentMethod(updatePaymentCommand.getNewPaymentMethod());

            // Güncellenmiş kaydı kaydet
            paymentRepository.save(payment);

            return new UpdatedPaymentResponse(payment.getId(), payment.getAmount(), payment.getPaymentMethod());
        }
    }
}
