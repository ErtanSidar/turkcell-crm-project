package com.turkcell.billingpaymentservice.cqrs.application.billing.command.create;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.application.billing.mapper.BillingMapper;
import com.turkcell.billingpaymentservice.cqrs.entities.Billing;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateBillingCommand implements Command<CreatedBillingResponse> {
    private UUID customerId;
    private UUID subscriptionId;
    private String billingPeriod;
    private Double totalAmount;
    private String status;

    @Component
    @RequiredArgsConstructor
    public static class CreateBillingCommandHandler implements
            Command.Handler<CreateBillingCommand, CreatedBillingResponse> {

        private final BillingRepository billingRepository;

        @Override
        public CreatedBillingResponse handle(CreateBillingCommand createBillingCommand) {
            BillingMapper billingMapper = BillingMapper.INSTANCE;
            Billing billing = billingMapper.billingFromCreateBillingCommand(createBillingCommand);
            billing.setDueDate(LocalDateTime.now());
            billingRepository.save(billing);
            return billingMapper.createdBillingResponseFromBilling(billing);
        }
    }
}
