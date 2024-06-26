package com.example.airdnb.exception;

public class AccommodationNotFoundException extends CustomEntityNotFoundException{
    public AccommodationNotFoundException(Long id) {
        super("accommodation", id);
    }
}
