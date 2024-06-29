package com.example.airdnb.controller;

import com.example.airdnb.domain.user.User;
import com.example.airdnb.domain.user.UserDetail;
import com.example.airdnb.dto.user.LoginRequest;
import com.example.airdnb.dto.user.LoginResponse;
import com.example.airdnb.dto.user.UserCreateRequest;
import com.example.airdnb.dto.user.UserResponse;
import com.example.airdnb.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(
        @RequestBody @Valid UserCreateRequest userCreateRequest) {
        Long userId = userService.join(userCreateRequest);
        return ResponseEntity.created(URI.create("/users/" + userId)).build();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return userService.findUserResponseById(userId);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/current-user")
    public String getCurrentUser(@AuthenticationPrincipal UserDetail userDetail) {
        return userDetail.getName();
    }
}
