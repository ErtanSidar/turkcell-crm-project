package com.turkcell.billingpaymentservice.cqrs.application.billing.query.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class GetBillingByIdQuery implements Command<GetBillingByIdResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class GetBillingByIdQueryHandler implements
            Command.Handler<GetBillingByIdQuery, GetBillingByIdResponse> {

        private final BillingRepository billingRepository;

        @Override
        public GetBillingByIdResponse handle(GetBillingByIdQuery getBillingByIdQuery) {
            return null;  // Burada veritabanından billing bilgisi getirilip döndürülmeli
        }
    }
}
