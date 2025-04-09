package com.turkcell.customerservice.config;

import io.github.ertansidar.security.configuration.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final BaseSecurityService baseSecurityService;

    private static final String ADMIN = "ADMIN";

    // Define path constants
    private static final String[] PATHS = {
            "/api/v1/addresses/**",
            "/api/v1/campaigns/**",
            "/api/v1/cities**",
            "/api/v1/contacts/**",
            "/api/v1/countries/**",
            "/api/v1/customers/**",
            "/api/v1/districts/**"
    };

    // Define resource names for matching
    private static final String[] RESOURCE_NAMES = {
            "Address", "Campaign", "City", "Contact", "Country", "Customer", "District"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Apply base security configuration
        http = baseSecurityService.configureCoreSecurity(http);

        // Iterate over path-authorization pairs to minimize repeated code
        for (int i = 0; i < PATHS.length; i++) {
            String path = PATHS[i];
            String resource = RESOURCE_NAMES[i];

            http.authorizeHttpRequests(req -> req
                    .requestMatchers(HttpMethod.DELETE, path).hasAnyAuthority(resource + ".Delete", ADMIN)
                    .requestMatchers(HttpMethod.POST, path).hasAnyAuthority(resource + ".Create", ADMIN)
                    .requestMatchers(HttpMethod.PUT, path).hasAnyAuthority(resource + ".Update", ADMIN)
                    .requestMatchers(HttpMethod.GET, path).hasAnyAuthority(resource + ".Update", ADMIN)
            );
        }

        // Allow all other requests
        http.authorizeHttpRequests(req -> req.anyRequest().permitAll());

        return http.build();
    }
}