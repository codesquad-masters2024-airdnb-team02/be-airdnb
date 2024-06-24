package com.example.airdnb.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomEntityNotFoundException extends EntityNotFoundException {
    private static final String ERROR_MESSAGE_FORMAT = "해당하는 id의 %s를 찾을 수 없습니다. id : %d";

    public CustomEntityNotFoundException(String entityName,Long id) {
        super(String.format(ERROR_MESSAGE_FORMAT, entityName, id));
    }
}
