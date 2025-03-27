package com.example.userservice.services.abstracts;

import com.example.userservice.services.dtos.user.CreateUserRequest;
import com.example.userservice.services.dtos.user.UserLoginRequest;

public interface UserService {

    void create(CreateUserRequest createUserRequest);

    String login(UserLoginRequest loginUserRequest);
}
