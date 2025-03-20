package com.turkcell.billingpaymentservice.cqrs.application.billing.rules;

import com.turkcell.billingpaymentservice.cqrs.entities.Billing;
import com.turkcell.billingpaymentservice.cqrs.persistance.billing.BillingRepository;
import io.github.ertansidar.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BillingBusinessRules {
    private final BillingRepository billingRepository;
    public void checkBillingIdExists(UUID id){
        Optional<Billing> billing = billingRepository.findById(id);
        if(billing.isEmpty()){
            throw new BusinessException("Billing not found");
        }
    }
}
