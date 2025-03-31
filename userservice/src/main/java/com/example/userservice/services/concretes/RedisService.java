package com.example.userservice.services.concretes;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public void saveToken(String token) {
        redisTemplate.opsForValue().set("token", token, 60, TimeUnit.MINUTES);
    }

    public String getToken() {
        return redisTemplate.opsForValue().get("token");
    }

    public void deleteToken() {
        redisTemplate.delete("token");
    }
}
