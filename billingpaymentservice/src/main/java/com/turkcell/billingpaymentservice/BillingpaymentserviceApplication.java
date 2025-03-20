package com.turkcell.billingpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@ComponentScan(basePackages = {"com.turkcell.billingpaymentservice", "io.github.ertansidar"})
public class BillingpaymentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingpaymentserviceApplication.class, args);
	}

}
