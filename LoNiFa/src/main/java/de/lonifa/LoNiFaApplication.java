package de.lonifa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.lonifa.user.domain.UserRepository;
import de.lonifa.security.InitDB;

@SpringBootApplication
@EnableScheduling
public class LoNiFaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoNiFaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return (args) -> {
			new InitDB().init(userRepository);
		};
	}
}
