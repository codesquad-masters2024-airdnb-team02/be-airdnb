package com.example.airdnb.config.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.airdnb.repository.mongo")
@EnableMongoAuditing
public class MongoConfig {
}