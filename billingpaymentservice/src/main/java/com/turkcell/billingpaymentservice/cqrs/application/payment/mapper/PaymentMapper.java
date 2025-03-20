package com.turkcell.billingpaymentservice.cqrs.application.payment.mapper;

import com.turkcell.billingpaymentservice.cqrs.application.payment.command.create.CreatePaymentCommand;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.create.CreatedPaymentResponse;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.update.UpdatePaymentCommand;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.update.UpdatedPaymentResponse;
import com.turkcell.billingpaymentservice.cqrs.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment paymentFromCreatePaymentCommand(CreatePaymentCommand createPaymentCommand);

    CreatedPaymentResponse createdPaymentResponseFromPayment(Payment payment);

    Payment paymentFromUpdatePaymentCommand(UpdatePaymentCommand updatePaymentCommand);

    UpdatedPaymentResponse updatedPaymentResponseFromPayment(Payment payment);
}
