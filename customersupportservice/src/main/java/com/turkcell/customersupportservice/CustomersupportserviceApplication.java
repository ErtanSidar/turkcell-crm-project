package com.turkcell.customersupportservice;

import com.turkcell.customersupportservice.client.FeignErrorDecoder;
import feign.codec.ErrorDecoder;
import io.github.ertansidar.annotations.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableCore
public class CustomersupportserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomersupportserviceApplication.class, args);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

}
