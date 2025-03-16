package com.turkcell.planservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("CUSTOMERSERVICE")
public interface UsageInterface {

    //todo customerservisin getOneCustomer methodu çağırılıcak
}
