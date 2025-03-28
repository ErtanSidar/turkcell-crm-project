package com.turkcell.customersupportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.turkcell.customersupportservice", "io.github.ertansidar"})
public class CustomersupportserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersupportserviceApplication.class, args);
	}

}
