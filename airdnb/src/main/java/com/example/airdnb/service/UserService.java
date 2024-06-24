package com.example.airdnb.service;

import com.example.airdnb.config.TokenProvider;
import com.example.airdnb.domain.user.User;
import com.example.airdnb.dto.user.LoginRequest;
import com.example.airdnb.dto.user.LoginResponse;
import com.example.airdnb.dto.user.UserCreateRequest;
import com.example.airdnb.dto.user.UserResponse;
import com.example.airdnb.repository.UserRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long join(UserCreateRequest request) {
        User user = User.builder()
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .name(request.name())
            .role(request.role())
            .build();

        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public UserResponse findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return UserResponse.fromUser(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(NoSuchElementException::new);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        String token = tokenProvider.generateToken(user);
        return LoginResponse.from(user, token);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
    }
}
