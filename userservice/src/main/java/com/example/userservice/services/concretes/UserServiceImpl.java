package com.example.userservice.services.concretes;

import com.essoft.event.user.UserCreatedEvent;
import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.abstracts.UserService;
import com.example.userservice.services.dtos.user.CreateUserRequest;
import com.example.userservice.services.dtos.user.UserLoginRequest;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.security.jwt.BaseJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BaseJwtService baseJwtService;
    private final StreamBridge streamBridge;


    @Override
    public void create(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        userCreatedEvent.setUsername(user.getUsername());
        userCreatedEvent.setEmail(user.getEmail());
        streamBridge.send("userCreatedFunction-out-0", userCreatedEvent);
    }

    @Override
    public String login(UserLoginRequest request) {
        User dbUser = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException("Invalid or wrong credentials."));

        boolean isPasswordCorrect = passwordEncoder
                .matches(request.getPassword(), dbUser.getPassword());

        if (!isPasswordCorrect)
            throw new BusinessException("Invalid or wrong credentials. ");
        Map<String, Object> roles = new HashMap<>();
        roles.put("roles", dbUser.getOperationClaims().stream().map(c -> c.getName()).toList());

        return this.baseJwtService.generateToken(dbUser.getUsername(), roles);
    }
}
