package com.example.airdnb.service;

import com.example.airdnb.domain.user.User;
import com.example.airdnb.domain.user.UserDetail;
import com.example.airdnb.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername name: {}", username);
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserDetail(user);
    }

}
