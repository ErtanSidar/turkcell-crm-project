package com.example.userservice.configuration;

import io.github.ertansidar.security.configuration.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final BaseSecurityService baseSecurityService;

    public SecurityConfiguration(BaseSecurityService baseSecurityService) {
        this.baseSecurityService = baseSecurityService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http = baseSecurityService.configureCoreSecurity(http);

        http.authorizeHttpRequests(req -> req.requestMatchers("/actuator/**").permitAll());
        http.authorizeHttpRequests(pack -> pack.requestMatchers("/api/v1/auth/**").permitAll());

        return http.build();
    }
}
