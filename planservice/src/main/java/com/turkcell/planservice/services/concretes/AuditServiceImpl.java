package com.turkcell.planservice.services.concretes;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditServiceImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Cebrail KAYA");
    }
}
