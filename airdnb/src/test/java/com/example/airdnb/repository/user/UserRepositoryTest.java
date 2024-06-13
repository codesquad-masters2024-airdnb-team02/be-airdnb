package com.example.airdnb.repository.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.airdnb.domain.user.User;
import com.example.airdnb.domain.user.User.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {
        User user = User.builder()
            .email("cori@naver.com")
            .name("cori")
            .password("1234")
            .role(Role.HOST)
            .build();

        User savedUser = userRepository.save(user);

        assertThat(user).usingRecursiveComparison().isEqualTo(savedUser);
    }

    @Test
    void updateUser() {
        User user = User.builder()
            .email("cori@naver.com")
            .name("cori")
            .password("1234")
            .role(Role.HOST)
            .build();

        User savedUser = userRepository.save(user);
        String newName = "simba";
        savedUser.changeName(newName);
        assertThat(user.getName()).isEqualTo(newName);
    }

}