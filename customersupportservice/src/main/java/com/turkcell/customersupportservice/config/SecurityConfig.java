package com.turkcell.customersupportservice.config;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http = baseSecurityService.configureCoreSecurity(http);
        http.authorizeHttpRequests(req -> req.requestMatchers("/actuator/**").permitAll());
        http.authorizeHttpRequests(ticket -> ticket.requestMatchers("/api/v1/tickets/**").permitAll());
        return http.build();
    }
}