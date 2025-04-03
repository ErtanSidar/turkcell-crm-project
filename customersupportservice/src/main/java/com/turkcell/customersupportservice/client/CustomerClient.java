package com.turkcell.customersupportservice.client;

import com.essoft.dto.customer.GetCustomerFeignResponse;
import com.essoft.dto.customer.GetCustomerResponse;
import com.essoft.dto.customer.GetIndividualCustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customerservice", url = "http://localhost:8083")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    GetCustomerFeignResponse findById(@PathVariable UUID id);
}
