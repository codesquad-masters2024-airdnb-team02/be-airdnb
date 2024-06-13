package com.example.airdnb.repository.user;

import com.example.airdnb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
