package com.example.mongospring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StrudentRepository repository){
		return args -> {
				Address address = new Address(
						"India",
						"Udaipur",
						"313001");
				Student student = new Student(
						"Puru",
						"Agarwal",
						"puru.agar99@gmail.com",
						Gender.MALE,
						address,
						List.of("Computer Science", "Maths"),
						BigDecimal.TEN,
						LocalDateTime.now());

				repository.insert(student);
		};
	}
}
