package com.turkcell.planservice.services.concretes;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditServiceImpl implements AuditorAware<String> {


    public static final String USER = "Cebrail Kaya";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(USER);
    }

}

