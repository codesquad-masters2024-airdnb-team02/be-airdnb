package com.example.airdnb.dto.user;

import com.example.airdnb.domain.user.Role;
import com.example.airdnb.domain.user.User;

public record UserResponse(
    Long id,
    String email,
    String name,
    Role role) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getRole());
    }
}
