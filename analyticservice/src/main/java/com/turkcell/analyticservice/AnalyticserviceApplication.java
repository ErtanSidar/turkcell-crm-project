package com.turkcell.analyticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.turkcell.analyticservice","io.github.ertansidar"})
public class AnalyticserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticserviceApplication.class, args);
	}

}
