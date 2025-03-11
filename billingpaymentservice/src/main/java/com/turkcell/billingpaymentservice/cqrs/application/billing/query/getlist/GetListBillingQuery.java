package com.turkcell.billingpaymentservice.cqrs.application.billing.query.getlist;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class GetListBillingQuery implements Command<List<GetListBillingResponse>> {

    @Component
    @RequiredArgsConstructor
    public static class GetListBillingQueryHandler implements
            Command.Handler<GetListBillingQuery, List<GetListBillingResponse>> {

        private final BillingRepository billingRepository;

        @Override
        public List<GetListBillingResponse> handle(GetListBillingQuery query) {
            return billingRepository.findAll()
                    .stream()
                    .map(billing -> new GetListBillingResponse(
                            billing.getId(),
                            billing.getTotalAmount(),
                            billing.getStatus()))
                    .collect(Collectors.toList());
        }
    }
}
