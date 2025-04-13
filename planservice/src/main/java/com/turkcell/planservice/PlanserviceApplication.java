package com.turkcell.planservice;

import com.turkcell.planservice.client.FeignErrorDecoder;
import feign.codec.ErrorDecoder;
import io.github.ertansidar.annotations.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCore
@EnableFeignClients
public class PlanserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PlanserviceApplication.class, args);
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new FeignErrorDecoder();
	}

}
