package com.turkcell.planservice.feign;

import com.turkcell.planservice.events.dtos.customerdtos.response.GetCustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("CUSTOMERSERVICE")
public interface CustomerClient {

    @GetMapping("/{id}")
    GetCustomerResponse findById(@PathVariable("id") UUID id);
}
