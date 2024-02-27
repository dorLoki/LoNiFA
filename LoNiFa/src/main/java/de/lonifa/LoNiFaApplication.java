package de.lonifa;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.lonifa.security.UserRole;
import de.lonifa.user.domain.User;
import de.lonifa.user.domain.UserRepository;

@SpringBootApplication
@EnableScheduling
public class LoNiFaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoNiFaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return (args) -> {
//			User Luke = new User("Luke", "luke", "MLtQ_FYsXwnk1U?mP^,n", new HashSet<UserRole>(
//					Arrays.asList(UserRole.ADMIN, UserRole.MOD_MINECRAFT, UserRole.MOD_PALWORLD)));
//			User Niklas = new User("Niklas", "niklas", "C8txZJAytQ}4n,.}hRNb",
//					new HashSet<UserRole>(Arrays.asList(UserRole.MOD_MINECRAFT, UserRole.MOD_PALWORLD)));
//			User mod_minecraft = new User("Mod Minecraft", "mod_minecraft", "n~y!0MQ2:NMY8w16-?i8",
//					new HashSet<UserRole>(Arrays.asList(UserRole.MOD_MINECRAFT)));
//			User mod_palworld = new User("Mod Palworld", "mod_palworld", "k>Wg0C7qj)KbXQ8#syj>",
//					new HashSet<UserRole>(Arrays.asList(UserRole.MOD_PALWORLD)));
//			User system = new User("System", "system", "fez*Lm1R@0?,1=~f>0ye", null);
//			userRepository.saveAll(Arrays.asList(Luke, Niklas, mod_minecraft, mod_palworld, system));
		};
	}
}
