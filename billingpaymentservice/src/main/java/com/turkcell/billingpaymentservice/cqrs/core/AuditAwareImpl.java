package com.turkcell.billingpaymentservice.cqrs.core;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    public static final String USER = "SILA TOPUZ";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(USER);
    }

}
