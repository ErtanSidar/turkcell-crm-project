package com.turkcell.planservice.config;

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

        // Özel security yapılandırması
        http.authorizeHttpRequests(req -> req.requestMatchers("/actuator/**").permitAll());
        http.authorizeHttpRequests(pack -> pack.requestMatchers("/api/v1/packages/**").permitAll());
        http.authorizeHttpRequests(plan -> plan.requestMatchers("/api/v1/plans/**").permitAll());
        http.authorizeHttpRequests(sub -> sub.requestMatchers("/api/v1/subscriptions/**").permitAll());
        http.authorizeHttpRequests(product -> product.requestMatchers("/api/v1/products/**").permitAll());
        http.authorizeHttpRequests(usage -> usage.requestMatchers("/api/v1/usages/**").permitAll());

        return http.build();
    }
}