package com.example.userservice.services.concretes;

import com.example.userservice.core.jwt.JwtService;
import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.abstracts.UserService;
import com.example.userservice.services.dtos.user.CreateUserRequest;
import com.example.userservice.services.dtos.user.UserLoginRequest;
import io.github.ertansidar.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;


    @Override
    public void add(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userRepository.save(user);

    }

    @Override
    public String login(UserLoginRequest request) {
        User dbUser = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException("Invalid or wrong credentials."));

        boolean isPasswordCorrect = bCryptPasswordEncoder
                .matches(request.getPassword(), dbUser.getPassword());

        if (!isPasswordCorrect)
            throw new BusinessException("Invalid or wrong credentials. ");
        Map<String, Object> roles = new HashMap<>();
        roles.put("roles", dbUser.getOperationClaims().stream().map(c -> c.getName()).toList());

        return this.jwtService.generateToken(dbUser.getUsername(), roles);
    }
}
