package com.turkcell.planservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class PlanserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PlanserviceApplication.class, args);
	}

}
