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
			"/static/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/img/**",
            "/scss/**",
            "/vendor/**",
            "/metis/auth/**"
            };
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors().and().csrf()
				.disable()
			.formLogin()
				.usernameParameter("email")
				.loginPage("/metis/auth/login")
				.defaultSuccessUrl("/metis/")
				.failureUrl("/metis/auth/login?error=true")
			.and()
				.authorizeRequests()
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
