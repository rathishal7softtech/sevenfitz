package com.seven.fitz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	    .cors(withDefaults())
	        .csrf(csrf -> csrf.disable())

	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                    "/swagger-ui/**",
	                    "/v3/api-docs/**"
	            ).permitAll()

	            .anyRequest().permitAll()
	        )

	        .sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	        );

	    return http.build();
	}
}


