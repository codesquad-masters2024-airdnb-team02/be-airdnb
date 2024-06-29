package com.example.airdnb.config.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.airdnb.repository.jpa")
public class JpaConfig {
}