package com.turkcell.billingpaymentservice.cqrs.application.payment.command.create;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.payment.PaymentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class CreatePaymentCommand implements Command<CreatedPaymentResponse> {
    private UUID customerId;
    private Double amount;
    private String paymentDate;
    private String paymentMethod;
    private String status;
    private UUID billingId;

    @Component
    @RequiredArgsConstructor
    public static class CreatePaymentCommandHandler implements
            Command.Handler<CreatePaymentCommand, CreatedPaymentResponse> {

        private final PaymentRepository paymentRepository;

        @Override
        public CreatedPaymentResponse handle(CreatePaymentCommand createPaymentCommand) {
            return null; // Buraya kayıt işlemi eklenecek.
        }
    }
}


