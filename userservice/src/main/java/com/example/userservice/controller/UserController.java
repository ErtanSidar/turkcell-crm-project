package com.example.userservice.controller;


import com.example.userservice.entities.User;
import com.example.userservice.services.abstracts.UserService;
import com.example.userservice.services.dtos.user.CreateUserRequest;
import com.example.userservice.services.dtos.user.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void add(@RequestBody CreateUserRequest request){
        userService.add(request);

    }

    @PostMapping("/login")
    public String login (@RequestBody UserLoginRequest request ) {
        return userService.login(request);
    }




}


