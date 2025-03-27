package com.example.userservice.controller;


import com.example.userservice.services.abstracts.UserService;
import com.example.userservice.services.dtos.user.CreateUserRequest;
import com.example.userservice.services.dtos.user.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void create(@RequestBody CreateUserRequest request) {
        userService.create(request);

    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

}


