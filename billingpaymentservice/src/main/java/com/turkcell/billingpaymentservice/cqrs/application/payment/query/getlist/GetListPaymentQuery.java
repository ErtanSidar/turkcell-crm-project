package com.turkcell.billingpaymentservice.cqrs.application.payment.query.getlist;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class GetListPaymentQuery implements Command<List<GetListPaymentResponse>> {

    @Component
    @RequiredArgsConstructor
    public static class GetListPaymentQueryHandler implements
            Command.Handler<GetListPaymentQuery, List<GetListPaymentResponse>> {

        private final PaymentRepository paymentRepository;

        @Override
        public List<GetListPaymentResponse> handle(GetListPaymentQuery query) {
            return paymentRepository.findAll()
                    .stream()
                    .map(payment -> new GetListPaymentResponse(
                            payment.getId(),
                            payment.getAmount(),
                            payment.getStatus()))
                    .collect(Collectors.toList());
        }
    }
}
