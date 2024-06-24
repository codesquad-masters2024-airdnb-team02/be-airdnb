package com.example.airdnb.exception;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ExceptionTest {

    @Test
    void exceptionMessageTest() {
        Long notExistingUserId = 100L;

        String expectedMessage = String.format("해당하는 id의 user를 찾을 수 없습니다. id : %d", notExistingUserId);

        assertThatThrownBy(() -> {
            throw new UserNotFoundException(notExistingUserId);
        })
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(expectedMessage);
    }
}
