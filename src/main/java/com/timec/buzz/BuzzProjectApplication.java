package com.timec.buzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BuzzProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuzzProjectApplication.class, args);
	}

}
