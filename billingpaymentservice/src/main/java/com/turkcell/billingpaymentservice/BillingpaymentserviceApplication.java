package com.turkcell.billingpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.turkcell.billingpaymentservice", "io.github.ertansidar"})
public class BillingpaymentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingpaymentserviceApplication.class, args);
	}

}
