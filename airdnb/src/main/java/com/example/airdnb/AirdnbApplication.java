package com.example.airdnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AirdnbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirdnbApplication.class, args);
	}

}
