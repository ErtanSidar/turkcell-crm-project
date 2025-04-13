package com.turkcell.planservice.client;

import com.essoft.dto.customer.GetCustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customerservice",url = "http://customerservice:8083")
public interface CustomerClient {

    @GetMapping("/{id}")
    GetCustomerResponse findById(@PathVariable UUID id);
}
