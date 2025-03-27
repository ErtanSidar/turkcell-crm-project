package com.example.userservice;

import io.github.ertansidar.annotations.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableCore
public class UserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

}
