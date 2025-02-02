package com.davendra.buzzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BuzzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuzzerApplication.class, args);
	}

}
