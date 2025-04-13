package com.turkcell.billingpaymentservice;

import io.github.ertansidar.annotations.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableCore
@EnableFeignClients
public class BillingpaymentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingpaymentserviceApplication.class, args);
	}

}
