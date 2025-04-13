package com.turkcell.billingpaymentservice.cqrs.config;

import io.github.ertansidar.security.configuration.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final BaseSecurityService baseSecurityService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http = baseSecurityService.configureCoreSecurity(http);

        http.authorizeHttpRequests(req -> req.requestMatchers("/actuator/**").permitAll());

        http.authorizeHttpRequests(pack -> pack.requestMatchers("/api/v1/billings/**").permitAll());
        http.authorizeHttpRequests(payment -> payment.requestMatchers("/api/v1/payments/**").permitAll());
        return http.build();


    }
}