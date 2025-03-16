package com.turkcell.planservice.feign;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("CUSTOMERSERVICE")
public interface SubscriptionInterface {

    //todo customerservisin getOneCustomer methodu alınacak
}
