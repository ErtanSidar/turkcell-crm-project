package com.turkcell.analyticservice;

import io.github.ertansidar.annotations.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCore
@EnableFeignClients(basePackages = "com.turkcell.analyticservice.clients")
public class AnalyticserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticserviceApplication.class, args);
	}

}
