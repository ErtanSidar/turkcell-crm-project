package com.example.userservice.services.concretes;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<String> {

    public static final String USER = "AYSE KOC";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(USER);
    }
}
