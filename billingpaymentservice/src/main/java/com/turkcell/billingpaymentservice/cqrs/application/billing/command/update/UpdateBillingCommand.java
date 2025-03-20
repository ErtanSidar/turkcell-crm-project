package com.turkcell.billingpaymentservice.cqrs.application.billing.command.update;

import an.awesome.pipelinr.Command;
import com.turkcell.billingpaymentservice.cqrs.application.billing.mapper.BillingMapper;
import com.turkcell.billingpaymentservice.cqrs.application.billing.rules.BillingBusinessRules;
import com.turkcell.billingpaymentservice.cqrs.entities.Billing;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class UpdateBillingCommand implements Command<UpdatedBillingResponse> {
    private UUID id;
    private String newBillingDetail;  // Örnek olarak yeni fatura detayı eklenmiştir.

    @Component
    @RequiredArgsConstructor
    public static class UpdateBillingCommandHandler implements Command.Handler<UpdateBillingCommand, UpdatedBillingResponse> {

        private final BillingRepository billingRepository;
        private final BillingBusinessRules billingBusinessRules;

        @Override
        public UpdatedBillingResponse handle(UpdateBillingCommand updateBillingCommand) {

            billingBusinessRules.checkBillingIdExists(updateBillingCommand.getId());
            BillingMapper billingMapper = BillingMapper.INSTANCE;
            Billing billing = billingMapper.billingFromUpdateBillingCommand(updateBillingCommand);
            billing.setId(updateBillingCommand.getId());
            billingRepository.save(billing);
            return billingMapper.updateBillingResponseFromBilling(billing);
        }
    }
}
