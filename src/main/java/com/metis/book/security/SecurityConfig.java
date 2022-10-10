package com.metis.book.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	String[] allowURL = {
            "/css/**",
            "/images/**",
            "/fonts/**",
            "/scripts/**",
            };
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors().and().csrf()
				.disable()
			.formLogin()
				.loginPage("/metis/auth/login")
			    .defaultSuccessUrl("/metis/", true)
			.and()
				
				.authorizeRequests()
					.antMatchers("/metis/auth/**")
						.permitAll()
					.antMatchers(allowURL)
						.permitAll()
					.anyRequest()
						.authenticated();
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
