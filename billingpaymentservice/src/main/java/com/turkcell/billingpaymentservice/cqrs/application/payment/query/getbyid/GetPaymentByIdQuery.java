package com.turkcell.billingpaymentservice.cqrs.application.payment.query.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.payment.PaymentRepository;
import com.turkcell.billingpaymentservice.cqrs.entities.Payment;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class GetPaymentByIdQuery implements Command<GetPaymentByIdResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class GetPaymentByIdQueryHandler implements
            Command.Handler<GetPaymentByIdQuery, GetPaymentByIdResponse> {

        private final PaymentRepository paymentRepository;

        @Override
        public GetPaymentByIdResponse handle(GetPaymentByIdQuery getPaymentByIdQuery) {
            Payment payment = paymentRepository.findById(getPaymentByIdQuery.getId())
                    .orElseThrow(() -> new RuntimeException("Payment not found"));

            return new GetPaymentByIdResponse(
                    payment.getId(),
                    payment.getCustomerId(),
                    payment.getAmount(),
                    payment.getPaymentDate(),
                    payment.getPaymentMethod(),
                    payment.getStatus()
            );
        }
    }
}
