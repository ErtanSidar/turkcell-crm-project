package com.turkcell.customerservice.config;

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
    private static final String[] WHITE_LIST_URLS = {
            "/actuator/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http = baseSecurityService.configureCoreSecurity(http);
        http.authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URLS).permitAll());
        http.authorizeHttpRequests(district -> district.requestMatchers("/api/v1/districts/**").permitAll());
        http.authorizeHttpRequests(city -> city.requestMatchers("/api/v1/cities/**").permitAll());
        http.authorizeHttpRequests(country -> country.requestMatchers("/api/v1/countries/**").permitAll());
        http.authorizeHttpRequests(customer -> customer.requestMatchers("/api/v1/customers/**").permitAll());

        return http.build();
    }
}