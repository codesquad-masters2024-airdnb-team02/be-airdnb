package com.example.airdnb.exception;

public class UserNotFoundException extends CustomEntityNotFoundException {
    public UserNotFoundException(Long id) {
        super("user", id);
    }
}
