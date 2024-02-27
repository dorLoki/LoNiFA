package de.sachsenCompany.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.sachsenCompany.service.UserDetailsServiceImpl;

/**
 * Eine Konfigurationsklasse für die Sicherheitseinstellungen der Anwendung.
 */
@EnableWebSecurity
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Security extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN") // Nur ADMIN-Rolle
				.antMatchers("/palworld/**", "js/palworld.js").hasAnyRole("MOD_PALWORLD", "ADMIN").antMatchers("/minecraft/**", "/js/minecraft.js")
				.hasAnyRole("MOD_MINECRAFT", "ADMIN").antMatchers("/profile").hasRole("USER") // Nur USER-Rolle
				.antMatchers("/**").permitAll() // Alles andere ohne Rolle
				.anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/", true).permitAll().and().logout()
				.permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
