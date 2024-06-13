package com.example.airdnb.dto.user;

import com.example.airdnb.domain.user.User;
import com.example.airdnb.domain.user.User.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateRequest(
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotBlank String name,
    @NotNull Role role
) {

    public User toEntity() {
        return User.builder()
            .email(email)
            .password(password)
            .name(name)
            .role(role)
            .build();

    }

}
