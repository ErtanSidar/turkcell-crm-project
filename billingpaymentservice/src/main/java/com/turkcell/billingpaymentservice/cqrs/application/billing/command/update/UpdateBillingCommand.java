package com.turkcell.billingpaymentservice.cqrs.application.billing.command.update;

import an.awesome.pipelinr.Command;
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

        @Override
        public UpdatedBillingResponse handle(UpdateBillingCommand updateBillingCommand) {
            // Billing işlemi güncelleme işlemi burada yapılacak
            // Örneğin, id ile veritabanından ilgili kayıt çekilip, güncellenir.

            return new UpdatedBillingResponse(updateBillingCommand.getId());  // Bu örnekte sadece id dönülüyor.
        }
    }
}
