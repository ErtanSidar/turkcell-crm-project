package com.turkcell.billingpaymentservice.cqrs.application.billing.command.create;

import an.awesome.pipelinr.Command;
import com.essoft.dto.customer.GetCustomerFeignResponse;
import com.essoft.dto.subscription.GetSubscriptionFeignResponse;
import com.turkcell.billingpaymentservice.cqrs.application.billing.mapper.BillingMapper;
import com.turkcell.billingpaymentservice.cqrs.client.CustomerClient;
import com.turkcell.billingpaymentservice.cqrs.client.SubscriptionClient;
import com.turkcell.billingpaymentservice.cqrs.entities.Billing;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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
        private final CustomerClient customerClient;
        private final SubscriptionClient subscriptionClient;

        @Override
        public CreatedBillingResponse handle(CreateBillingCommand createBillingCommand) {

            GetCustomerFeignResponse customer = customerClient.findById(createBillingCommand.getCustomerId());
            GetSubscriptionFeignResponse subscription = subscriptionClient.findById(createBillingCommand.getCustomerId());

            BillingMapper billingMapper = BillingMapper.INSTANCE;
            Billing billing = billingMapper.billingFromCreateBillingCommand(createBillingCommand);
            billing.setDueDate(LocalDateTime.now());
            billingRepository.save(billing);
            return billingMapper.createdBillingResponseFromBilling(billing);
        }
    }
}
