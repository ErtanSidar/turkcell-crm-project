package com.turkcell.customersupportservice;

import io.github.ertansidar.annotations.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCore
public class CustomersupportserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomersupportserviceApplication.class, args);
    }

}
