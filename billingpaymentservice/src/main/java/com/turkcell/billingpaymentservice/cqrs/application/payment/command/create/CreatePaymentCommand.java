package com.turkcell.billingpaymentservice.cqrs.application.payment.command.create;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.application.payment.mapper.PaymentMapper;
import com.turkcell.billingpaymentservice.cqrs.entities.Payment;
import com.turkcell.billingpaymentservice.cqrs.persistance.payment.PaymentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreatePaymentCommand implements Command<CreatedPaymentResponse> {
    private UUID customerId;
    private Double amount;
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
            PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
            Payment payment = paymentMapper.paymentFromCreatePaymentCommand(createPaymentCommand);
            payment.setPaymentDate(LocalDateTime.now());
            paymentRepository.save(payment);
            return paymentMapper.createdPaymentResponseFromPayment(payment);
        }
    }
}


