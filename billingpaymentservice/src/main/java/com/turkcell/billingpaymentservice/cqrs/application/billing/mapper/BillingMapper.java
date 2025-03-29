package com.turkcell.billingpaymentservice.cqrs.application.billing.mapper;

import com.turkcell.billingpaymentservice.cqrs.application.billing.command.create.CreateBillingCommand;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.create.CreatedBillingResponse;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.update.UpdateBillingCommand;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.update.UpdatedBillingResponse;
import com.turkcell.billingpaymentservice.cqrs.entities.Billing;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillingMapper {
    BillingMapper INSTANCE = Mappers.getMapper(BillingMapper.class);

    Billing billingFromCreateBillingCommand(CreateBillingCommand createBillingCommand);

    CreatedBillingResponse createdBillingResponseFromBilling(Billing billing);

    Billing billingFromUpdateBillingCommand(UpdateBillingCommand updateBillingCommand);

    UpdatedBillingResponse updateBillingResponseFromBilling(Billing billing);
}
