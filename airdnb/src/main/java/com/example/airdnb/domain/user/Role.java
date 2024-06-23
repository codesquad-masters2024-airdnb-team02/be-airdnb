package com.example.airdnb.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    HOST("ROLE_HOST"),
    GUEST("ROLE_GUEST"),
    ADMIN("ROLE_ADMIN");

    private final String key;

}