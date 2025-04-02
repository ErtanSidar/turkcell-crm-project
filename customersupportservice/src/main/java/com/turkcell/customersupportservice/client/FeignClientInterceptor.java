package com.turkcell.customersupportservice.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {

//    private final RedisService redisService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
//        requestTemplate.header("Authorization", "Bearer " + getJwtToken());
//        requestTemplate.header("Accept", "application/json");
    }

//    private String getJwtToken() {
//        return redisService.getTokenFromRedis();
//    }
}
