package com.turkcell.billingpaymentservice.cqrs.application.payment.rules;

import com.turkcell.billingpaymentservice.cqrs.entities.Payment;
import com.turkcell.billingpaymentservice.cqrs.persistance.payment.PaymentRepository;
import io.github.ertansidar.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository paymentRepository;
    public void checkPaymentIdExists(UUID id){
        Optional<Payment> payment = paymentRepository.findById(id);
        if(payment.isEmpty()){
            throw new BusinessException("Payment not found");
        }
    }
}
