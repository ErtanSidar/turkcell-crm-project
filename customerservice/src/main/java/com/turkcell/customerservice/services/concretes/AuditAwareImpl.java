package com.turkcell.customerservice.services.concretes;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    public static final String USER = "ERTAN SIDAR";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(USER);
    }

}
