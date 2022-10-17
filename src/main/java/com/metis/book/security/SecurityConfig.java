package com.metis.book.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
            "/auth/**"
            };
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors().and().csrf()
				.disable()
			.formLogin()
				.usernameParameter("email")
				.loginPage("/auth/login")
				.loginProcessingUrl("/auth/login")
				.successHandler(myLoginSuccessHandler())
				.failureHandler(myLoginFailureHandler())
			.and()
				.logout()
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/")
			.and()
				.authorizeRequests()
					.antMatchers(allowURL)
						.permitAll()
					.antMatchers("/member/**")
						.hasAuthority("ROLE_USER")
					.antMatchers("/admin/**")
						.hasAuthority("ROLE_ADMIN")
					.anyRequest()
						.permitAll();
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationSuccessHandler myLoginSuccessHandler(){
	    return new LoginSuccessHandler();
	}
	
	@Bean
	AuthenticationFailureHandler myLoginFailureHandler() {
		return new LoginFailureHandler();
	}

}
