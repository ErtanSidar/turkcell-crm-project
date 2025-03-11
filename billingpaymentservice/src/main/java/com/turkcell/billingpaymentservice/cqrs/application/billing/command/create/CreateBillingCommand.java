package com.turkcell.billingpaymentservice.cqrs.application.billing.command.create;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class CreateBillingCommand implements Command<CreatedBillingResponse> {
    private UUID customerId;
    private UUID subscriptionId;
    private String billingPeriod;
    private Double totalAmount;
    private String dueDate;
    private String status;

    @Component
    @RequiredArgsConstructor
    public static class CreateBillingCommandHandler implements
            Command.Handler<CreateBillingCommand, CreatedBillingResponse> {

        private final BillingRepository billingRepository;

        @Override
        public CreatedBillingResponse handle(CreateBillingCommand createBillingCommand) {
            return null; // Buraya kayıt işlemi eklenecek.
        }
    }
}
