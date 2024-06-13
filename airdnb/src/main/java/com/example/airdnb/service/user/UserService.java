package com.example.airdnb.service.user;

import com.example.airdnb.domain.user.User;
import com.example.airdnb.dto.user.UserCreateRequest;
import com.example.airdnb.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long join(UserCreateRequest userCreateRequest) {
        User savedUser = userRepository.save(userCreateRequest.toEntity());
        return savedUser.getId();
    }

}
